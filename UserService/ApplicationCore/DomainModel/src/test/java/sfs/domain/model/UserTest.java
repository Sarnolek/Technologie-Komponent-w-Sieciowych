package sfs.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithCorrectInitialState() {
        User user = new User("123", "jkowalski", "pass123", "Jan", "Kowalski", "CLIENT", true);

        assertEquals("123", user.getId());
        assertEquals("jkowalski", user.getLogin());
        assertTrue(user.isActive());
    }

    @Test
    void shouldChangeActiveState() {
        User user = new User("123", "jkowalski", "pass123", "Jan", "Kowalski", "CLIENT", true);

        user.setActive(false);

        assertFalse(user.isActive(), "Stan użytkownika powinien zmienić się na nieaktywny.");
    }
}