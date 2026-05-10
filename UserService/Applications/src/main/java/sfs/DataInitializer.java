package sfs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import sfs.adapter.data.UserEnt;
import sfs.domain.model.User;
import sfs.ports.api.UserService;

@Profile("!test")
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    public DataInitializer(UserService userService, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        // Czyszczenie kolekcji użytkowników przed startem
        mongoTemplate.dropCollection(UserEnt.class);

        // Tworzenie przykładowych użytkowników (id = null, Mongo samo wygeneruje)
        User admin = new User(null, "kdawid", "haslo1", "Karol", "Dawid", "ADMIN", true);
        User manager = new User(null, "jkowalski", "haslo2", "Jan", "Kowalski", "MANAGER", true);
        User client1 = new User(null, "mchodulski", "haslo3", "Mateusz", "Chodulski", "CLIENT", true);
        User client2 = new User(null, "anowak", "haslo4", "Anna", "Nowak", "CLIENT", true);

        userService.createUser(admin);
        userService.createUser(manager);
        userService.createUser(client1);
        userService.createUser(client2);

        System.out.println("=========================================");
        System.out.println("ZAINICJALIZOWANO BAZĘ UŻYTKOWNIKÓW POMYŚLNIE!");
        System.out.println("=========================================");
    }
}