package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.OrderRep;
import kg.mega.saloon.mappers.ClientMapper;
import kg.mega.saloon.mappers.MasterMapper;
import kg.mega.saloon.mappers.OrderMapper;
import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.dto.SaloonDto;
import kg.mega.saloon.models.requests.SaveOrderRequest;
import kg.mega.saloon.network.EmailSender;
import kg.mega.saloon.service.ClientService;
import kg.mega.saloon.service.EmailSenderService;
import kg.mega.saloon.service.MasterService;
import kg.mega.saloon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRep rep;
    OrderMapper mapper = OrderMapper.INSTANCE;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MasterService masterService;

    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public OrderDto save(OrderDto order) {
        return mapper.toDto(rep.save(mapper.toEntity(order)));
    }

    @Override
    public OrderDto create(SaveOrderRequest order) {

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
            emailSenderService.emailSender(orderDto.getClient().getEmail(), orderDto.getMaster().getSaloon().getName(),orderDto.getAppointment_date(), orderDto.getClient().getName());
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

}
