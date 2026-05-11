package sfs.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sfs.domain.exception.ResourceConflictException;
import sfs.domain.model.Client;
import sfs.domain.model.Gym;
import sfs.domain.model.Rental;
import sfs.ports.infrastructure.ClientRepositoryPort;
import sfs.ports.infrastructure.RentalRepositoryPort;
import sfs.ports.infrastructure.SportsFacilityRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @Mock
    private SportsFacilityRepositoryPort sportsFacilityRepositoryPort;

    @Mock
    private RentalRepositoryPort rentalRepositoryPort;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Test
    void shouldThrowExceptionWhenFacilityAlreadyRented() {
        String clientId = "client-1";
        String facilityId = "gym-1";
        LocalDateTime start = LocalDateTime.of(2025, 5, 10, 12, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 10, 14, 0);

        when(clientRepositoryPort.findById(clientId)).thenReturn(Optional.of(new Client(clientId, "Jan", "Kowalski")));
        when(sportsFacilityRepositoryPort.findById(facilityId)).thenReturn(Optional.of(new Gym("Gym", 50, 100, 200, true)));

        Rental existingRental = new Rental("client-2", facilityId, start.minusHours(1), start.plusHours(1));
        when(rentalRepositoryPort.findByFacilityId(facilityId)).thenReturn(List.of(existingRental));

        assertThrows(ResourceConflictException.class, () ->
                rentalService.rentFacility(clientId, facilityId, start, end)
        );

        verify(rentalRepositoryPort, never()).save(any());
    }

    @Test
    void shouldSaveRentalWhenFacilityIsAvailable() throws Exception {
        String clientId = "client-1";
        String facilityId = "gym-1";
        LocalDateTime start = LocalDateTime.of(2025, 5, 10, 15, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 10, 17, 0);

        when(clientRepositoryPort.findById(clientId)).thenReturn(Optional.of(new Client(clientId, "Jan", "Kowalski")));
        when(sportsFacilityRepositoryPort.findById(facilityId)).thenReturn(Optional.of(new Gym("Gym", 50, 100, 200, true)));
        when(rentalRepositoryPort.findByFacilityId(facilityId)).thenReturn(List.of());

        Rental savedRental = new Rental(clientId, facilityId, start, end);
        savedRental.setId("rental-123");
        when(rentalRepositoryPort.save(any(Rental.class))).thenReturn(savedRental);

        Rental result = rentalService.rentFacility(clientId, facilityId, start, end);

        assertNotNull(result);
        assertEquals("rental-123", result.getId());
        verify(rentalRepositoryPort, times(1)).save(any(Rental.class));
    }
}