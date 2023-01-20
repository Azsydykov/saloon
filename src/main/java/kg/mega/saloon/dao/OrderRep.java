package kg.mega.saloon.dao;

import kg.mega.saloon.enums.OrderStatusEnum;
import kg.mega.saloon.models.entities.Order;
import kg.mega.saloon.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRep extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM tb_order WHERE master_id=:id", nativeQuery = true)
    List<Order> getOrderByMasterId(Long id);

    @Query(value = "SELECT * FROM tb_order WHERE status=:status", nativeQuery = true)
    List<Order> getOrderByStatus(String status);


}
