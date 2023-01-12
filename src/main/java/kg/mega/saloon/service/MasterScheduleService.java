package kg.mega.saloon.service;

import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.models.dto.MasterScheduleDto;
import kg.mega.saloon.models.responses.Response;
import kg.mega.saloon.models.responses.ScheduleResponse;

import java.util.List;
import java.util.Map;

public interface MasterScheduleService extends BaseService<MasterScheduleDto> {

    Response create(Long masterId, List<Long> scheduleIds);

    Map<WorkDayEnum,String> getSchedule(Long masterId);
}
