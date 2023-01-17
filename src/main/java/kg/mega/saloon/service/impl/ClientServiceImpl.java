package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.ClientRep;
import kg.mega.saloon.exceptions.ClientNotFoundException;
import kg.mega.saloon.mappers.ClientMapper;
import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ClientServiceImpl implements ClientService {
    ClientMapper mapper = ClientMapper.INSTANCE;

    private final ClientRep rep;
    public ClientServiceImpl(ClientRep rep) {
        this.rep = rep;
    }


    @Override
    public ClientDto save(ClientDto clientDto) {
        return mapper.toDto(rep.save(mapper.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new ClientNotFoundException("Клиент не найден!")));
    }

    @Override
    public ClientDto delete(Long id) {
        ClientDto client = findById(id);
        client.setActive(false);
        return save(client);
    }

    @Override
    public List<ClientDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

}
