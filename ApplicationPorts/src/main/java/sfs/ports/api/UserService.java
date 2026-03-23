package sfs.ports.api;

import sfs.domain.model.Admin;
import sfs.domain.model.Client;
import sfs.domain.model.FacilityManager;
import sfs.domain.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws Exception;
    Client createClient(Client client) throws Exception;
    Admin createAdmin(Admin admin) throws Exception;
    FacilityManager createFacilityManager(FacilityManager manager) throws Exception;

    User updateUser(String id, String firstName, String lastName) throws Exception;

    User getUserById(String id) throws Exception;
    List<User> getAllUsers();
    User activateUser(String id) throws Exception;
    User deactivateUser(String id) throws Exception;
    User findUserByLogin(String login) throws Exception;
    List<User> findUserByLoginFragment(String loginFragment) throws Exception;
}