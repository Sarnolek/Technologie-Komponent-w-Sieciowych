package sfs.ports.api;

import sfs.domain.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(String id, String firstName, String lastName);
    User getUserById(String id);
    List<User> getAllUsers();
    User activateUser(String id) throws Exception;
    User deactivateUser(String id) throws Exception;
    User findUserByLogin(String login);
    List<User> findUserByLoginFragment(String loginFragment);
}