package sfs.services;

import org.springframework.stereotype.Service;
import sfs.domain.exception.ResourceNotFoundException;
import sfs.domain.model.Client;
import sfs.ports.api.ClientService;
import sfs.ports.infrastructure.ClientRepositoryPort;
import io.micrometer.core.annotation.Timed;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientServiceImpl(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    @Timed(value = "client.creation.time", description = "Czas potrzebny na przetworzenie profilu klienta z RabbitMQ")
    public Client createClient(Client client) {
        return clientRepositoryPort.save(client);
    }

    @Override
    public Client getClient(String id) {
        return clientRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Klient o ID: " + id + " nie istnieje."));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryPort.findAll();
    }
}