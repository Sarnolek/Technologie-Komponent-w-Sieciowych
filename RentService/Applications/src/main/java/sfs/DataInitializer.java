//package sfs;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//import sfs.domain.model.*;
//import sfs.ports.api.ClientService;
//import sfs.ports.api.RentalService;
//import sfs.ports.api.SportsFacilityService;
//import java.time.LocalDateTime;
//
//@Profile("!test")
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final ClientService clientService;
//    private final SportsFacilityService sportsFacilityService;
//    private final RentalService rentalService;
//    private final MongoTemplate mongoTemplate;
//
//    // Zwróć uwagę: NIE MA TU UserService!
//    public DataInitializer(ClientService clientService,
//                           SportsFacilityService sportsFacilityService,
//                           RentalService rentalService,
//                           MongoTemplate mongoTemplate) {
//        this.clientService = clientService;
//        this.sportsFacilityService = sportsFacilityService;
//        this.rentalService = rentalService;
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Czyścimy kolekcje lokalne RentService
//        mongoTemplate.dropCollection("facilities");
//        mongoTemplate.dropCollection("rentals");
//        mongoTemplate.dropCollection("clients");
//
//        System.out.println("Inicjalizacja danych w RentService...");
//
//        // 1. Tworzymy obiekty sportowe
//        SportsFacility gym = sportsFacilityService.createGym(
//                new Gym("FitFabric", 50.0, 200, 50, true)
//        );
//        System.out.println("Dodano obiekt: " + gym.getId());
//
//        // 2. Tworzymy lokalną kopię klienta (z ID, które znamy z UserService)
//        // W prawdziwym systemie to ID przyszłoby przez RabbitMQ/Kafkę
//        Client client1 = clientService.createClient(
//                new Client("mchodulski-id", "Mateusz", "Chodulski")
//        );
//
//        // 3. Tworzymy wypożyczenie
//        try {
//            rentalService.rentFacility(
//                    client1.getId(),
//                    gym.getId(),
//                    LocalDateTime.now().plusDays(1),
//                    LocalDateTime.now().plusDays(1).plusHours(2)
//            );
//            System.out.println("Dodano wypożyczenie pomyślnie!");
//        } catch (Exception e) {
//            System.err.println("Błąd inicjalizacji rezerwacji: " + e.getMessage());
//        }
//
//        System.out.println("=========================================");
//        System.out.println("RENT SERVICE: BAZA GOTOWA!");
//        System.out.println("=========================================");
//    }
//}