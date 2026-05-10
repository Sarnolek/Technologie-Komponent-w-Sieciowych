package sfs.adapter.aggregates;

import org.springframework.stereotype.Component;
import sfs.adapter.data.UserEnt;
import sfs.adapter.mappers.UserMapper;
import sfs.adapter.repository.MongoUserRepository;
import sfs.domain.model.User;
import sfs.ports.infrastructure.UserRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final MongoUserRepository mongoRepository;

    public UserRepositoryAdapter(MongoUserRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public User save(User user) {
        UserEnt entToSave = UserMapper.toEntity(user);
        UserEnt savedEnt = mongoRepository.save(entToSave);
        return UserMapper.toDomain(savedEnt);
    }

    @Override
    public Optional<User> findById(String id) {
        return mongoRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return mongoRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return mongoRepository.findByLogin(login).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findByLoginFragment(String loginFragment) {
        return mongoRepository.findByLoginContainingIgnoreCase(loginFragment).stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }
}