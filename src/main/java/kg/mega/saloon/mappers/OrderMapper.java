package kg.mega.saloon.mappers;

import kg.mega.saloon.models.dto.OrderDto;
import kg.mega.saloon.models.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
