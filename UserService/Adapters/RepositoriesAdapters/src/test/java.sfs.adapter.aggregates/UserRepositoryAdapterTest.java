package sfs.adapter.aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sfs.adapter.repository.MongoUserRepository;
import sfs.domain.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
@Import(UserRepositoryAdapter.class)
class UserRepositoryAdapterTest {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @BeforeEach
    void setUp() {
        mongoUserRepository.deleteAll();
    }

    @Test
    void shouldSaveAndRetrieveUser() {
        User user = new User(null, "jdoe", "secret", "John", "Doe", "CLIENT", true);

        User savedUser = userRepositoryAdapter.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("jdoe", savedUser.getLogin());

        Optional<User> retrieved = userRepositoryAdapter.findById(savedUser.getId());
        assertTrue(retrieved.isPresent());
        assertEquals("secret", retrieved.get().getPassword());
        assertEquals("CLIENT", retrieved.get().getRole());
    }
}