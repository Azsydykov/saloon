package kg.mega.saloon.mappers;

import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.entities.Master;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface MasterMapper extends BaseMapper<Master, MasterDto>{
    MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);
}
