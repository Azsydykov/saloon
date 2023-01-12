package kg.mega.saloon.service;

import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.requests.SaveOrderRequest;

import javax.mail.MessagingException;
import java.io.IOException;

public interface OrderService extends BaseService<OrderDto>{


    OrderDto create(SaveOrderRequest order);

}
