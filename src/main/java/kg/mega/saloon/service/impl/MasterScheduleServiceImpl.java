package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.MasterScheduleRep;
import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.mappers.MasterScheduleMapper;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.MasterScheduleDto;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.responses.Response;
import kg.mega.saloon.service.MasterScheduleService;
import kg.mega.saloon.service.MasterService;
import kg.mega.saloon.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MasterScheduleServiceImpl implements MasterScheduleService {

    MasterScheduleMapper mapper = MasterScheduleMapper.INSTANCE;

    private final MasterScheduleRep rep;
    private final MasterService masterService;
    private final ScheduleService scheduleService;


    public MasterScheduleServiceImpl(MasterScheduleRep rep, MasterService masterService, ScheduleService scheduleService) {
        this.rep = rep;
        this.masterService = masterService;
        this.scheduleService = scheduleService;

    }

    @Override
    public MasterScheduleDto save(MasterScheduleDto masterScheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(masterScheduleDto)));
    }

    @Override
    public MasterScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("График мастеров не найден!")));
    }

    @Override
    public MasterScheduleDto delete(Long id) {
        return null;
    }

    @Override
    public List<MasterScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public Response create(Long masterId, List<Long> scheduleIds) {
        MasterDto masterDto = masterService.findById(masterId);

        for (Long id : scheduleIds) {
            ScheduleDto scheduleDto = scheduleService.findById(id);
            save(new MasterScheduleDto(masterDto, scheduleDto));
        }
        return new Response("Success");
    }

    @Override
    public Map<WorkDayEnum, String> getSchedule(Long masterId) {

        List<ScheduleDto> scheduleList = scheduleService.getScheduleByMasterId(masterId);
        Map<WorkDayEnum, String> scheduleMap = new HashMap<>();

        for (ScheduleDto item : scheduleList) {
            scheduleMap.put(item.getWorkDay(), item.getStartTime()+" - "+item.getEndTime());
        }
        return scheduleMap;
    }

}