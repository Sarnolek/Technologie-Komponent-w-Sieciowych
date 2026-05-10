package sfs.ports.infrastructure;

import sfs.domain.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    Client save(Client client);
    Optional<Client> findById(String id);
    List<Client> findAll();
    void deleteById(String id);
}