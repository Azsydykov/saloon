package kg.mega.saloon.service;

import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.requests.OrderRequest;

import java.util.List;

public interface OrderService extends BaseService<OrderDto> {

    Object create(OrderRequest order);

    Object confirm(int code, Long orderId);

    void checkSuspendOrders();

    List<OrderDto> findOrderByMasterId(Long id);

    List<OrderDto> getOrderByStatus(String status);
}
