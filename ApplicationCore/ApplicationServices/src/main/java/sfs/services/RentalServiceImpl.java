package sfs.services;

import org.springframework.stereotype.Service;
import sfs.domain.exception.RentalException;
import sfs.domain.exception.ResourceConflictException;
import sfs.domain.exception.ResourceNotFoundException;
import sfs.domain.model.Client;
import sfs.domain.model.Rental;
import sfs.domain.model.SportsFacility;
import sfs.domain.model.User;
import sfs.ports.api.RentalService;
import sfs.ports.infrastructure.RentalRepositoryPort;
import sfs.ports.infrastructure.SportsFacilityRepositoryPort;
import sfs.ports.infrastructure.UserRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    private final UserRepositoryPort userRepositoryPort;
    private final SportsFacilityRepositoryPort sportsFacilityRepositoryPort;
    private final RentalRepositoryPort rentalRepositoryPort;

    private final Object rentalLock = new Object();

    public RentalServiceImpl(UserRepositoryPort userRepositoryPort, SportsFacilityRepositoryPort sportsFacilityRepositoryPort, RentalRepositoryPort rentalRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
        this.sportsFacilityRepositoryPort = sportsFacilityRepositoryPort;
        this.rentalRepositoryPort = rentalRepositoryPort;
    }

    @Override
    public Rental rentFacility(String clientId, String facilityId, LocalDateTime startTime, LocalDateTime endTime) throws RentalException {
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new RentalException("Czas rozpoczęcia wypożyczenia obiektu musi być przed czasem zakończenia");
        }

        User user = userRepositoryPort.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Użytkownik o ID: " + clientId + " nie istnieje."));

        if (!(user instanceof Client)){
            throw new RentalException("Użytkownik o ID: " + clientId + " nie jest klientem.");
        }

        if (!user.isActive()){
            throw new RentalException("Klient o ID: " + clientId + " nie jest aktywny.");
        }

        SportsFacility sportsFacility = sportsFacilityRepositoryPort.findById(facilityId).orElseThrow(() -> new ResourceNotFoundException("Obiekt sportowy o ID: " + facilityId + " nie istnieje."));

        // na PAS trzeba sprawdzić czy klient jest aktywny
        synchronized (rentalLock) {
            if (!isFacilityAvailable(facilityId, startTime, endTime)) {
                throw new ResourceConflictException("Obiekt sportowy o ID: " + facilityId + " jest juz wypozyczony.");
                // metoda poniżej to sprawdza
            }

            Rental newRental = new Rental(clientId, facilityId, startTime, endTime);
            return rentalRepositoryPort.save(newRental);
        }
    }

    @Override
    public boolean isFacilityAvailable(String facilityId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Rental> existingRentals = rentalRepositoryPort.findByFacilityId(facilityId);

        for(Rental existing : existingRentals){
            if(existing.overlaps(startTime, endTime)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Rental> getRentalsForClient(String clientId) {
        return rentalRepositoryPort.findByClientId(clientId);
    }

    @Override
    public List<Rental> getRentalsForFacility(String facilityId) {
        return rentalRepositoryPort.findByFacilityId(facilityId);
    }

    @Override
    public List<Rental> getPastRentalsForClient(String clientId) {
        return rentalRepositoryPort.findPastByClientId(clientId, LocalDateTime.now());
    }

    @Override
    public List<Rental> getCurrentRentalsForClient(String clientId) {
        return rentalRepositoryPort.findCurrentByClientId(clientId, LocalDateTime.now());
    }

    @Override
    public List<Rental> getPastRentalsForFacility(String facilityId) {
        return rentalRepositoryPort.findPastByFacilityId(facilityId, LocalDateTime.now());
    }

    @Override
    public List<Rental> getCurrentRentalsForFacility(String facilityId) {
        return rentalRepositoryPort.findCurrentByFacilityId(facilityId, LocalDateTime.now());
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepositoryPort.findAll();
    }

    // zakończenie alokacji polega na ustawieniu atrybutu czasu zakończenia alokacji
    @Override
    public Rental endRental(String id) throws RentalException {
        Rental rental =  rentalRepositoryPort.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono rezerwacji o ID: " + id));
        rental.setEndTime(LocalDateTime.now());
        return rentalRepositoryPort.save(rental);
    }

    // usuwanie alokacji dotyczy tylko alokacji nie zakończonych
    @Override
    public void deleteRental(String id) throws RentalException {
        Rental rental = rentalRepositoryPort.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wypożyczenie o ID: " + id + " nie istnieje."));

        if (rental.getEndTime() != null && rental.getEndTime().isBefore(LocalDateTime.now())) {
            throw new RentalException("Nie można usunąć rezerwacji o ID: " + id + ", ponieważ jest to rezerwacja zakończona.");
        } else {
            rentalRepositoryPort.deleteById(id);
        }
    }
}
