package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sfs.adapter.rest.dto.CreateClientRequest;
import sfs.domain.model.Client;
import sfs.domain.model.User;
import sfs.ports.api.UserService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {
    private final UserService userService;

    public ClientRestController(UserService userService) {
        this.userService = userService;
    }

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