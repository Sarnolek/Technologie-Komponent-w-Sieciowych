package sfs.adapter.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.view.UserViewPort;
import sfs.ports.view.dto.UpdateUserRequest;

import java.util.List;

@Component
@WebService(serviceName = "UserService")
public class UserSoapAdapter implements UserViewPort {

    private final UserService userService;

    public UserSoapAdapter(UserService userService) {
        this.userService = userService;
    }

    @Override
    @WebMethod
    public User getUserById(String id) throws Exception {
        return userService.getUserById(id);
    }

    @Override
    @WebMethod
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @WebMethod
    public User activateUser(String id) throws Exception {
        return userService.activateUser(id);
    }

    @Override
    @WebMethod
    public User deactivateUser(String id) throws Exception {
        return userService.deactivateUser(id);
    }

    @Override
    @WebMethod
    public List<User> findUserByLoginFragment(String loginFragment) throws Exception {
        return userService.findUserByLoginFragment(loginFragment);
    }

    @Override
    @WebMethod
    public User findUserByLogin(String login) throws Exception {
        return userService.findUserByLogin(login);
    }

    @Override
    @WebMethod
    public User updateUser(String id, UpdateUserRequest request) throws Exception {
        return userService.updateUser(id, request.getFirstName(), request.getLastName());
    }
}