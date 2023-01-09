package kg.mega.saloon.mappers;

import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.models.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ClientMapper extends BaseMapper<Client, ClientDto> {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
