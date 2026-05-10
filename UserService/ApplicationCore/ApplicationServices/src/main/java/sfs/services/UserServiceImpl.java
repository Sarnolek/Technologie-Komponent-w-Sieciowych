package sfs.services;

import org.springframework.stereotype.Service;
import sfs.domain.exception.ResourceNotFoundException;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.infrastructure.UserRepositoryPort;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User updateUser(String id, String firstName, String lastName) {
        User user = getUserById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepositoryPort.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono użytkownika o ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User activateUser(String id) throws Exception {
        User user = getUserById(id);
        if(user.isActive()){
            throw new Exception("Użytkownik o ID: " + user.getId() + " już został aktywowany.");
        }
        user.setActive(true);
        return userRepositoryPort.save(user);
    }

    @Override
    public User deactivateUser(String id) throws Exception {
        User user = getUserById(id);
        if(!user.isActive()){
            throw new Exception("Użytkownik o ID: " + user.getId() + " już był nieaktywny.");
        }
        user.setActive(false);
        return userRepositoryPort.save(user);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepositoryPort.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Użytkownik o loginie: " + login + " nie został odnaleziony."));
    }

    @Override
    public List<User> findUserByLoginFragment(String loginFragment) {
        return userRepositoryPort.findByLoginFragment(loginFragment);
    }
}