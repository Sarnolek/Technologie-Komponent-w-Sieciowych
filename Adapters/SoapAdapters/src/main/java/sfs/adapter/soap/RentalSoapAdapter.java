package sfs.adapter.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import sfs.domain.model.Rental;
import sfs.domain.exception.RentalException;
import sfs.ports.api.RentalService;
import sfs.ports.view.RentalViewPort;
import sfs.ports.view.dto.CreateRentalRequest;

import java.util.List;

@Component
@WebService(serviceName = "RentalService")
public class RentalSoapAdapter implements RentalViewPort {

    private final RentalService rentalService;

    public RentalSoapAdapter(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Override
    @WebMethod
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @Override
    @WebMethod
    public List<Rental> getRentalsForFacility(String facilityId) {
        return rentalService.getRentalsForFacility(facilityId);
    }

    @Override
    @WebMethod
    public List<Rental> getRentalsForClient(String clientId) {
        return rentalService.getRentalsForClient(clientId);
    }

    @Override
    @WebMethod
    public Rental rentFacility(CreateRentalRequest request) throws RentalException {
        return rentalService.rentFacility(request.getClientId(), request.getFacilityId(), request.getStartTime(), request.getEndTime());
    }

    @Override
    @WebMethod
    public List<Rental> getPastRentalsForClient(String clientId) {
        return rentalService.getPastRentalsForClient(clientId);
    }

    @Override
    @WebMethod
    public List<Rental> getCurrentRentalsForClient(String clientId) {
        return rentalService.getCurrentRentalsForClient(clientId);
    }

    @Override
    @WebMethod
    public List<Rental> getPastRentalsForFacility(String facilityId) {
        return rentalService.getPastRentalsForFacility(facilityId);
    }

    @Override
    @WebMethod
    public List<Rental> getCurrentRentalsForFacility(String facilityId) {
        return rentalService.getCurrentRentalsForFacility(facilityId);
    }

    @Override
    @WebMethod
    public Rental endRental(String id) throws RentalException {
        return rentalService.endRental(id);
    }

    @Override
    @WebMethod
    public void deleteRental(String id) throws RentalException {
        rentalService.deleteRental(id);
    }
}


