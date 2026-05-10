package sfs.ports.view;

import sfs.domain.model.User;
import sfs.ports.view.dto.UpdateUserRequest;

import java.util.List;

public interface UserViewPort {
    User getUserById(String id) throws Exception;
    List<User> getAllUsers();
    User activateUser(String id) throws Exception;
    User deactivateUser(String id) throws Exception;
    List<User> findUserByLoginFragment(String loginFragment) throws Exception;
    User findUserByLogin(String login) throws Exception;
    User updateUser(String id, UpdateUserRequest request) throws Exception;
}