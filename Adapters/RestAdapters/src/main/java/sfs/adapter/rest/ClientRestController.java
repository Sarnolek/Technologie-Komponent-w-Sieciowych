package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.Client;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.view.ClientViewPort;
import sfs.ports.view.dto.CreateClientRequest;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController implements ClientViewPort {
    private final UserService userService;

    public ClientRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createClient(@Valid @RequestBody CreateClientRequest request) throws Exception {
        Client client = new Client();
        client.setLogin(request.getLogin());
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());

        return userService.createClient(client);
    }
}