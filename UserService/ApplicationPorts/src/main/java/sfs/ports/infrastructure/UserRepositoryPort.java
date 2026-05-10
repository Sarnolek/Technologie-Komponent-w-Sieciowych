package sfs.ports.infrastructure;

import sfs.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    void deleteById(String id);
    Optional<User> findByLogin(String login);
    List<User> findByLoginFragment(String loginFragment);
}