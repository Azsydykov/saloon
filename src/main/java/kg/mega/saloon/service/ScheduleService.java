package kg.mega.saloon.service;

import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.requests.SaveMasterRequest;
import kg.mega.saloon.models.requests.SaveScheduleRequest;
import kg.mega.saloon.models.responses.Response;
import kg.mega.saloon.models.responses.ScheduleResponse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScheduleService extends BaseService<ScheduleDto> {

    List<ScheduleDto> getScheduleByMasterName(String name);
    List<ScheduleDto> getScheduleByMasterId(Long id);

    ScheduleDto create(WorkDayEnum workDay, LocalTime startTime, LocalTime endTime);


}
