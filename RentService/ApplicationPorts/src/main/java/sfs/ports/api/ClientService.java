package sfs.ports.api;

import sfs.domain.model.Client;
import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    Client getClient(String id);
    List<Client> getAllClients();
}