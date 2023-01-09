package kg.mega.saloon.mappers;

import kg.mega.saloon.models.dto.SaloonDto;
import kg.mega.saloon.models.entities.Saloon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SaloonMapper extends BaseMapper<Saloon, SaloonDto> {
    SaloonMapper INSTANCE = Mappers.getMapper(SaloonMapper.class);
}
