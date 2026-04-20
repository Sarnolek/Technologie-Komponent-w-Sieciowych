package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.Admin;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.view.AdminViewPort;
import sfs.ports.view.dto.CreateAdminRequest;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminRestController implements AdminViewPort {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createAdmin(@Valid @RequestBody CreateAdminRequest request) throws Exception {
        Admin admin = new Admin();
        admin.setLogin(request.getLogin());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());

        return userService.createAdmin(admin);
    }
}