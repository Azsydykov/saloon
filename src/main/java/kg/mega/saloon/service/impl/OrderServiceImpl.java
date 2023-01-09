package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.OrderRep;
import kg.mega.saloon.mappers.ClientMapper;
import kg.mega.saloon.mappers.MasterMapper;
import kg.mega.saloon.mappers.OrderMapper;
import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.models.dto.OrderDto;
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
    private EmailSenderService emailSenderService;


    @Override
    public OrderDto save(OrderDto order) {
        ClientDto client = new ClientDto();
        client.setName(order.getClient().getName());
        client.setSurname(order.getClient().getSurname());
        client.setPhoneNumber(order.getClient().getPhoneNumber());
        client.setEmail(order.getClient().getEmail());
        client = clientService.save(client);
        order.setClient(client);

        if (client.getName().isEmpty() | client.getPhoneNumber().isEmpty()) {
            throw new RuntimeException("Имя или номер телефона не может быть пустым!");
        }
        try {
            emailSenderService.emailSender(order.getClient().getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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

}
