package sfs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import sfs.domain.model.Client;
import sfs.domain.model.Gym;
import sfs.ports.api.ClientService;
import sfs.ports.api.RentalService;
import sfs.ports.api.SportsFacilityService;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = "sfs.*")
public class RestSfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestSfsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ClientService clientService,
                           SportsFacilityService sportsFacilityService,
                           RentalService rentalService,
                           MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("====== WYMUSZONA INICJALIZACJA DANYCH ======");
            mongoTemplate.dropCollection("facilities");
            mongoTemplate.dropCollection("rentals");
            mongoTemplate.dropCollection("clients");

            try {
                // Tworzymy siłownie
                var gym = sportsFacilityService.createGym(new Gym("Gym-Test", 40.0, 100, 50, true));

                // Tworzymy klienta
                var client = clientService.createClient(new Client("test-client-1", "Jan", "Testowy"));

                // Wypożyczamy
                rentalService.rentFacility(
                        client.getId(), gym.getId(),
                        LocalDateTime.now().plusDays(1),
                        LocalDateTime.now().plusDays(1).plusHours(2)
                );
                System.out.println("====== BAZA ZAŁADOWANA SUKCESEM ======");
            } catch (Exception e) {
                System.out.println("Błąd podczas inicjalizacji: " + e.getMessage());
            }
        };
    }
}