package kg.mega.saloon.mappers;

import kg.mega.saloon.models.dto.MasterScheduleDto;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.entities.MasterSchedule;
import kg.mega.saloon.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MasterScheduleMapper extends BaseMapper<MasterSchedule, MasterScheduleDto>{
    MasterScheduleMapper INSTANCE = Mappers.getMapper(MasterScheduleMapper.class);
}
