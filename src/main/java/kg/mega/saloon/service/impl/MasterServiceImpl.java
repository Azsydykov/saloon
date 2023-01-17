package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.MasterRep;
import kg.mega.saloon.exceptions.MasterNotFoundException;
import kg.mega.saloon.mappers.MasterMapper;
import kg.mega.saloon.models.dto.MasterDto;
import kg.mega.saloon.models.dto.SaloonDto;
import kg.mega.saloon.models.requests.SaveMasterRequest;
import kg.mega.saloon.service.MasterService;
import kg.mega.saloon.service.SaloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {
    MasterMapper mapper = MasterMapper.INSTANCE;

    private final MasterRep rep;
    private final SaloonService saloonServ;

    public MasterServiceImpl(MasterRep rep, SaloonService saloonServ) {
        this.rep = rep;
        this.saloonServ = saloonServ;
    }


    @Override
    public MasterDto save(MasterDto master) {

        return mapper.toDto(rep.save(mapper.toEntity(master)));
    }

    @Override
    public MasterDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new MasterNotFoundException("Мастер не найден!")));
    }

    @Override
    public MasterDto delete(Long id) {
        MasterDto master = findById(id);
        master.setActive(false);
        return save(master);
    }

    @Override
    public List<MasterDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }


    @Override
    public MasterDto create(SaveMasterRequest master) {
        SaloonDto salon = saloonServ.findById(master.getSaloonId());
        MasterDto masterDto = new MasterDto();
        masterDto.setName(master.getName());
        masterDto.setSurname(master.getSurname());
        masterDto.setWorkType(master.getWorkType());
        masterDto.setSaloon(salon);

        return save(masterDto);
    }
}
