package sfs.ports.view;

import sfs.domain.exception.RentalException;
import sfs.domain.model.Rental;
import sfs.ports.view.dto.CreateRentalRequest;

import java.util.List;

public interface RentalViewPort {
    List<Rental> getAllRentals();
    List<Rental> getRentalsForFacility(String facilityId);
    List<Rental> getRentalsForClient(String clientId);
    Rental rentFacility(CreateRentalRequest request) throws RentalException;
    List<Rental> getPastRentalsForClient(String clientId);
    List<Rental> getCurrentRentalsForClient(String clientId);
    List<Rental> getPastRentalsForFacility(String facilityId);
    List<Rental> getCurrentRentalsForFacility(String facilityId);
    Rental endRental(String id) throws RentalException;
    void deleteRental(String id) throws RentalException;
}

