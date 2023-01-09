package kg.mega.saloon.service.impl;

import kg.mega.saloon.dao.ClientRep;
import kg.mega.saloon.mappers.ClientMapper;
import kg.mega.saloon.models.dto.ClientDto;
import kg.mega.saloon.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRep rep;

    ClientMapper mapper = ClientMapper.INSTANCE;

    @Override
    public ClientDto save(ClientDto clientDto) {
        return mapper.toDto(rep.save(mapper.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Клиент не найден!")));
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
