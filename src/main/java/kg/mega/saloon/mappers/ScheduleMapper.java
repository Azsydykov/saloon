package kg.mega.saloon.mappers;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule, ScheduleDto>{
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

}