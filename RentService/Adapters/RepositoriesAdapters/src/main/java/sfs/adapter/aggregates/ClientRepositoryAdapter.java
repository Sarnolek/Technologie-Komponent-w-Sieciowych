package sfs.adapter.aggregates;

import org.springframework.stereotype.Component;
import sfs.adapter.mappers.ClientMapper;
import sfs.adapter.repository.MongoClientRepository;
import sfs.domain.model.Client;
import sfs.ports.infrastructure.ClientRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final MongoClientRepository repository;
    private final ClientMapper mapper;

    public ClientRepositoryAdapter(MongoClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client client) {
        return mapper.toDomain(repository.save(mapper.toEntity(client)));
    }

    @Override
    public Optional<Client> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}