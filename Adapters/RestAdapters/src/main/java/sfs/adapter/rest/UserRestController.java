package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.User;
import sfs.adapter.rest.dto.UpdateUserRequest;
import sfs.ports.api.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    public final UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws Exception {
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/activate")
    public User activateUser(@PathVariable String id) throws Exception {
        return userService.activateUser(id);
    }

    @PutMapping("/{id}/deactivate")
    public User deactivateUser(@PathVariable String id) throws Exception {
        return userService.deactivateUser(id);
    }

    @GetMapping("/search/contains")
    public List<User> findUserByLoginFragment(@RequestParam String loginFragment) throws Exception{
        return userService.findUserByLoginFragment(loginFragment);
    }

    @GetMapping("/search/exact")
    public User findUserByLogin(@RequestParam String login) throws Exception {
        return userService.findUserByLogin(login);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request) throws Exception{
        return userService.updateUser(id, request.getFirstName(), request.getLastName());
    }
}