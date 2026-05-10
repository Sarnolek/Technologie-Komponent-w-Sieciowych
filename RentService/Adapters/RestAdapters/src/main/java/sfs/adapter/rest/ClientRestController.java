package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.Client;
import sfs.ports.api.ClientService;
import sfs.ports.view.ClientViewPort;
import sfs.ports.view.dto.CreateClientRequest;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController implements ClientViewPort {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody CreateClientRequest request) throws Exception{
        Client client = new Client(request.getId(), request.getFirstName(), request.getLastName());
        return clientService.createClient(client);
    }
}