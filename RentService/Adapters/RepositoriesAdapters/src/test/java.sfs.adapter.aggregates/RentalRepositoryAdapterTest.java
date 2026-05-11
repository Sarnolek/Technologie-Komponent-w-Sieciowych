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
import sfs.adapter.repository.MongoRentalRepository;
import sfs.domain.model.Rental;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
@Import(RentalRepositoryAdapter.class)
class RentalRepositoryAdapterTest {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private RentalRepositoryAdapter rentalRepositoryAdapter;

    @Autowired
    private MongoRentalRepository mongoRentalRepository;

    @BeforeEach
    void setUp() {
        mongoRentalRepository.deleteAll();
    }

    @Test
    void shouldSaveRentalAndFindItByClientId() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(1).plusHours(2);
        Rental rental = new Rental("client-123", "facility-456", start, end);

        rentalRepositoryAdapter.save(rental);

        List<Rental> rentals = rentalRepositoryAdapter.findByClientId("client-123");

        assertEquals(1, rentals.size());
        assertEquals("facility-456", rentals.get(0).getFacilityId());
    }
}