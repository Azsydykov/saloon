package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.ScheduleRep;
import kg.mega.saloon.mappers.ScheduleMapper;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRep rep;

    ScheduleMapper mapper = ScheduleMapper.INSTANCE;


    @Override
    public ScheduleDto save(ScheduleDto masterSchedule) {
        return mapper.toDto(rep.save(mapper.toEntity(masterSchedule)));
    }

    @Override
    public ScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("График мастера не найден!")));
    }

    @Override
    public ScheduleDto delete(Long id) {
        ScheduleDto masterSchedule = findById(id);
        //????
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
}
