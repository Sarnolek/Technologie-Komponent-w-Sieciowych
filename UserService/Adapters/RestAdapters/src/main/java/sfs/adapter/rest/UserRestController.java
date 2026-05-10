package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sfs.ports.view.UserViewPort;
import sfs.ports.view.dto.UpdateUserRequest;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.view.dto.UpdateUserRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController implements UserViewPort {

    public final UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @Override
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws Exception {
        return userService.getUserById(id);
    }

    @Override
    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @Override
    @PutMapping("/{id}/activate")
    public User activateUser(@PathVariable String id) throws Exception {
        return userService.activateUser(id);
    }

    @Override
    @PutMapping("/{id}/deactivate")
    public User deactivateUser(@PathVariable String id) throws Exception {
        return userService.deactivateUser(id);
    }

    @Override
    @GetMapping("/search/contains")
    public List<User> findUserByLoginFragment(@RequestParam String loginFragment) throws Exception{
        return userService.findUserByLoginFragment(loginFragment);
    }

    @Override
    @GetMapping("/search/exact")
    public User findUserByLogin(@RequestParam String login) throws Exception {
        return userService.findUserByLogin(login);
    }

    @Override
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request) throws Exception{
        return userService.updateUser(id, request.getFirstName(), request.getLastName());
    }
}