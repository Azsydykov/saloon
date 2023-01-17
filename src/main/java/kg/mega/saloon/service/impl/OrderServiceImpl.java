package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.OrderRep;
import kg.mega.saloon.enums.OrderStatusEnum;
import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.exceptions.ExceptionHandler;
import kg.mega.saloon.mappers.OrderMapper;
import kg.mega.saloon.models.dto.*;
import kg.mega.saloon.models.requests.OrderRequest;
import kg.mega.saloon.models.requests.SaveOrderRequest;
import kg.mega.saloon.models.responses.Response;
import kg.mega.saloon.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

@Service

@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;


    private final OrderRep rep;
    private final ClientService clientService;
    private final MasterService masterService;
    private final EmailSenderService emailSenderService;
    private final ScheduleService scheduleService;


    public OrderServiceImpl(OrderRep rep, ClientService clientService, MasterService masterService, ScheduleService scheduleService, EmailSenderService emailSenderService) {
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
    public OrderDto create1(SaveOrderRequest order) {

        ClientDto client = new ClientDto();
        MasterDto masterDto = masterService.findById(order.getMasterId());
        OrderDto orderDto = new OrderDto();
        client.setName(order.getClientDto().getName());
        client.setSurname(order.getClientDto().getSurname());
        client.setPhoneNumber(order.getClientDto().getPhoneNumber());
        client.setEmail(order.getClientDto().getEmail());

        client = clientService.save(order.getClientDto());
        orderDto.setClient(client);
        orderDto.setMaster(masterDto);
        orderDto.setAppointment_date(order.getAppointment_date());


        if (client.getName().isEmpty() | client.getPhoneNumber().isEmpty()) {
            throw new RuntimeException("Имя или номер телефона не может быть пустым!");
        }
        try {
            emailSenderService.emailSender(orderDto.getClient().getEmail(), orderDto.getMaster().getSaloon().getName(), orderDto.getAppointment_date());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return save(orderDto);
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
        //TODO add exc with 404 code  /done

        ClientDto clientDto = clientService.findById(order.getClientId());


        MasterDto masterDto = masterService.findById(order.getMasterId());

        List<ScheduleDto> scheduleDtos = scheduleService.getScheduleByMasterId(masterDto.getId());

        ScheduleDto scheduleDto = new ScheduleDto();  //не должны создавать новый график??????

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(order.getAppointmentDate());
        WorkDayEnum workDayEnum = WorkDayEnum.getValue(calendar.get(Calendar.DAY_OF_WEEK));

        for (ScheduleDto item : scheduleDtos) {
            if (item.getWorkDay().equals(workDayEnum)) {
                scheduleDto = item;
                break;
            } else {
                throw new RuntimeException("В этот день мастер не работает!");
            }
        }
        //проверка по графику мастера /done
        //проверка на ордерс  /done

        LocalTime startTime = scheduleDto.getStartTime();
        LocalTime endTime = scheduleDto.getEndTime();

        LocalTime appointmentTime = LocalDateTime.ofInstant(order.getAppointmentDate().toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        if (appointmentTime.isAfter(startTime) & appointmentTime.isBefore(endTime)) {

        } else {
            throw new RuntimeException("Извините, но мастер не работает в это время!");
        }
        SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderDto orderDto = new OrderDto();
        List<OrderDto> orderDtoList = findAll();

        for (OrderDto item : orderDtoList) {
            String adppDate=sdm.format(item.getAppointment_date());
            String newAppDate=sdm.format(order.getAppointmentDate());

            if (adppDate.equals(newAppDate)) {
                throw new RuntimeException("Извините, на данное время клиент уже записан!");
            } else continue;
        }
        orderDto.setMaster(masterDto);
        orderDto.setClient(clientDto);
        orderDto.setAppointment_date(order.getAppointmentDate());
        orderDto.setStatus(OrderStatusEnum.CONFIRM);
        save(orderDto);
        try {
            emailSenderService.emailSender(orderDto.getClient().getEmail(),
                                           orderDto.getMaster().getSaloon().getName(),
                                           orderDto.getAppointment_date());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new Response("Registration completed successfully!");
    }
}
