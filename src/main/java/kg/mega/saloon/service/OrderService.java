package kg.mega.saloon.service;

import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.requests.OrderRequest;
import kg.mega.saloon.models.requests.SaveOrderRequest;

public interface OrderService extends BaseService<OrderDto>{


    OrderDto create1(SaveOrderRequest order);
    Object create(OrderRequest order);

}
