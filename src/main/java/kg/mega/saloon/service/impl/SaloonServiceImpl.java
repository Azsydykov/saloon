package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.SaloonRep;
import kg.mega.saloon.mappers.SaloonMapper;
import kg.mega.saloon.models.dto.SaloonDto;
import kg.mega.saloon.service.SaloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaloonServiceImpl implements SaloonService {
    SaloonMapper mapper = SaloonMapper.INSTANCE;


    private final SaloonRep rep;
    public SaloonServiceImpl(SaloonRep rep) {
        this.rep = rep;
    }


    @Override
    public SaloonDto save(SaloonDto saloon) {
        return mapper.toDto(rep.save(mapper.toEntity(saloon)));
    }

    @Override
    public SaloonDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()-> new RuntimeException("Салон не найден!")));
    }

    @Override
    public SaloonDto delete(Long id) {
        SaloonDto saloon = findById(id);
        saloon.setActive(false);
        return save(saloon);
    }

    @Override
    public List<SaloonDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
