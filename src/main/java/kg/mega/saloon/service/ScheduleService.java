package kg.mega.saloon.service;

import kg.mega.saloon.models.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService extends BaseService<ScheduleDto>{

    List<ScheduleDto> getScheduleByMasterName (String name);
}
