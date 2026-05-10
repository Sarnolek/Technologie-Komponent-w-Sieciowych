package sfs.ports.view;

import sfs.domain.model.Client;
import sfs.ports.view.dto.CreateClientRequest;

public interface ClientViewPort {
    Client createClient(CreateClientRequest request) throws Exception;
}