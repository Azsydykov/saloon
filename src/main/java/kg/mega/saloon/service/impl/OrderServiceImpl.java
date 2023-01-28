package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.OrderRep;
import kg.mega.saloon.enums.OrderStatusEnum;
import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.exceptions.RegistrationException;
import kg.mega.saloon.mappers.OrderMapper;
import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.requests.OrderRequest;
import kg.mega.saloon.models.responses.Response;
import kg.mega.saloon.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service

@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;
    Random random = new Random();
    int confirmCode = random.ints(10000, 99999).findFirst().getAsInt();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final OrderRep rep;
    private final ClientService clientService;
    private final MasterService masterService;
    private final EmailSenderService emailSenderService;
    private final ScheduleService scheduleService;


    public OrderServiceImpl(OrderRep rep, ClientService clientService, MasterService masterService,
                            ScheduleService scheduleService, EmailSenderService emailSenderService) {
        this.rep = rep;
        this.clientService = clientService;
        this.masterService = masterService;
        this.emailSenderService = emailSenderService;
        this.scheduleService = scheduleService;
    }


    @Override
    public OrderDto save(OrderDto order) {
        return mapper.toDto(rep.save(mapper.toEntity(order)));
    }



    @Override
    public OrderDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Заявка не найдена!")));
    }

    @Override
    public OrderDto delete(Long id) {
        OrderDto order = findById(id);
        order.setActive(false);
        return save(order);
    }

    @Override
    public List<OrderDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public Response create(OrderRequest order) {
        //Найти клиента, если его нет, ошибка код 404  /done
        //Найти мастера,если нет 404  /done
        //Найти график мастера на этот appointmentDate  /done
        //Найти день недели appointmentDate /done
        //По дню недели найти график /done
        // add exc with 404 code  /done

        ClientDto clientDto = clientService.findById(order.getClientId());
        MasterDto masterDto = masterService.findById(order.getMasterId());
        List<ScheduleDto> scheduleDtos = scheduleService.getScheduleByMasterId(masterDto.getId());
        ScheduleDto scheduleDto = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getAppointmentDate());
        WorkDayEnum workDayEnum = WorkDayEnum.getValue(calendar.get(Calendar.DAY_OF_WEEK));

        for (ScheduleDto item : scheduleDtos) {
            if (item.getWorkDay().equals(workDayEnum)) {
                scheduleDto = item;
                break;
            }
        }
        if (scheduleDto == null) {
            throw new RegistrationException("В этот день мастер не работает!");
        }
        //проверка по графику мастера /done
        //проверка на ордерс  /done

        LocalTime startTime = scheduleDto.getStartTime();
        LocalTime endTime = scheduleDto.getEndTime();

        LocalTime appointmentTime = LocalDateTime.ofInstant(order.getAppointmentDate().toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        if (!(appointmentTime.isAfter(startTime) & appointmentTime.isBefore(endTime))) {

            throw new RegistrationException("Извините, но мастер не работает в это время!");
        }

        OrderDto orderDto = new OrderDto();
        List<OrderDto> orderDtoList = findOrderByMasterId(masterDto.getId()); //ищим у графика мастера  /done

        //проверка на статус если canseled можно записывать  //done
        // если suspend проверяем на час если не прошёл ->извините на данное время клиент уже записан попробуйте позже  //done
        //                               если прошёл час тогда можем сохранять с изменение статуса прошлой заявки на canseled  //done

        for (OrderDto item : orderDtoList) {

            String appointmentDate = sdf.format(item.getAppointment_date());
            String newAppointmentDate = sdf.format(order.getAppointmentDate());

            if (appointmentDate.equals(newAppointmentDate)) {
                if(item.getStatus().equals(OrderStatusEnum.CONFIRM)){
                    throw new RegistrationException("Извините, на данное время клиент уже записан!");
                }
                else if (item.getStatus().equals(OrderStatusEnum.SUSPEND)){
                      if(!(checkDate(item.getUpdateDate()))) {
                        throw new RegistrationException("Извините, на данное время клиент уже записан, попробуйте позже!");
                    }else item.setStatus(OrderStatusEnum.CANCELED);
                          save(item);
                }
            }else continue;
        }
        orderDto.setMaster(masterDto);
        orderDto.setClient(clientDto);
        orderDto.setAppointment_date(order.getAppointmentDate());
        save(orderDto);

        try {
            emailSenderService.emailSender(orderDto.getClient().getEmail(),
                    orderDto.getMaster().getSaloon().getName(),
                    orderDto.getAppointment_date(),
                    confirmCode); //отправляем рандомный код для подтверждения
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new Response("Registration completed successfully!");
    }


    @Override
    public Object confirm(int code, Long orderId) {
        //проверка на код, если не верный то ошибку  /done
        //добавить проверку на время, если прошел час обработать ошибкой, если нет то идём дальше /done
        //переводим заявку в статус confirm //done

        OrderDto orderDto = findById(orderId);
        if (confirmCode != code) {
            throw new RegistrationException("Неверный код подтверждения, прошу повторить!");
        } else if (checkDate(orderDto.getUpdateDate())) {
            orderDto.setStatus(OrderStatusEnum.CANCELED);
            save(orderDto);
            throw new RegistrationException("Прошел час, регистрация не подтверждена!");
        } else orderDto.setStatus(OrderStatusEnum.CONFIRM);
        save(orderDto);
        return new Response("Успешное подтверждение кода");
    }


    @Override
    public void checkSuspendOrders() {
        //вытащить все заявки со статусом suspend  /done
        //если прошёл час то переводим в статус CANCELED  /done

        List<OrderDto> orderDtos = findAll();
        for (OrderDto item : orderDtos) {
            if (item.getStatus().equals(OrderStatusEnum.SUSPEND)) {
                if (checkDate(item.getUpdateDate())) {
                    item.setStatus(OrderStatusEnum.CANCELED);
                    save(item);
                }
            }
        }
    }


    @Override
    public List<OrderDto> findOrderByMasterId(Long id) {
        return mapper.toDtos(rep.getOrderByMasterId(id));
    }

    private boolean checkDate(Date updateDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(updateDate);
        calendar.add(Calendar.HOUR, 1);
        return new Date().after(calendar.getTime());
    }

    @Override
    public List<OrderDto> getOrderByStatus(String status) {
        return mapper.toDtos(rep.getOrderByStatus(status));
    }
}
