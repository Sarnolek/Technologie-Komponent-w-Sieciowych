import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.mongodb.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sfs.RestSfsApplication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = RestSfsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class RestSfsApplicationTests {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Mockujemy dekoder JWT, aby przepuścił nasz testowy token
    @MockBean
    private JwtDecoder jwtDecoder;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        mongoTemplate.dropCollection("users");

        // Konfiguracja mocka: jakikolwiek token wejdzie, uznaj go za poprawny
        Jwt jwt = Jwt.withTokenValue("fake-token")
                .header("alg", "RS256")
                .claim("sub", "testuser")
                .build();
        when(jwtDecoder.decode(anyString())).thenReturn(jwt);
    }

    @Test
    void shouldFetchUsersViaRestApi() {
        given()
                .header("Authorization", "Bearer fake-token") // Dodajemy fałszywy token do żądania
                .when()
                .get("/api/v1/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }
}