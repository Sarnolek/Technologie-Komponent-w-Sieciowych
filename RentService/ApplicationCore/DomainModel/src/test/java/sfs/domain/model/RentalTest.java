package sfs.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    @Test
    void shouldReturnTrueWhenDatesOverlap() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 10, 12, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 10, 14, 0);
        Rental rental = new Rental("client-1", "facility-1", start, end);

        assertTrue(rental.overlaps(
                LocalDateTime.of(2025, 5, 10, 13, 0),
                LocalDateTime.of(2025, 5, 10, 15, 0)
        ));

        assertTrue(rental.overlaps(
                LocalDateTime.of(2025, 5, 10, 12, 30),
                LocalDateTime.of(2025, 5, 10, 13, 30)
        ));
    }

    @Test
    void shouldReturnFalseWhenDatesDoNotOverlap() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 10, 12, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 10, 14, 0);
        Rental rental = new Rental("client-1", "facility-1", start, end);

        assertFalse(rental.overlaps(
                LocalDateTime.of(2025, 5, 10, 10, 0),
                LocalDateTime.of(2025, 5, 10, 12, 0)
        ));

        assertFalse(rental.overlaps(
                LocalDateTime.of(2025, 5, 10, 14, 0),
                LocalDateTime.of(2025, 5, 10, 16, 0)
        ));
    }
}