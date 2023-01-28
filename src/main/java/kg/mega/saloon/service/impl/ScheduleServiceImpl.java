package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.ScheduleRep;
import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.exceptions.ScheduleNotFoundException;
import kg.mega.saloon.mappers.ScheduleMapper;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.SaloonDto;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.requests.SaveScheduleRequest;
import kg.mega.saloon.models.responses.ScheduleResponse;
import kg.mega.saloon.service.MasterService;
import kg.mega.saloon.service.SaloonService;
import kg.mega.saloon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRep rep;

    ScheduleMapper mapper = ScheduleMapper.INSTANCE;


    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(scheduleDto)));
    }

    @Override
    public ScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new ScheduleNotFoundException("График мастера не найден!")));
    }

    @Override
    public ScheduleDto delete(Long id) {
        ScheduleDto masterSchedule = findById(id);
        return null;
    }

    @Override
    public List<ScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<ScheduleDto> getScheduleByMasterName(String name) {
        return mapper.toDtos(rep.getScheduleByMasterName(name));
    }

    @Override
    public List<ScheduleDto> getScheduleByMasterId(Long id) {
        return mapper.toDtos(rep.getScheduleByMasterId(id));
    }


    @Override
    public ScheduleDto create(WorkDayEnum workDay, LocalTime startTime, LocalTime endTime) {

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setWorkDay(workDay);
        scheduleDto.setStartTime(startTime);
        scheduleDto.setEndTime(endTime);

        return mapper.toDto(rep.save(mapper.toEntity(scheduleDto)));
    }

}
