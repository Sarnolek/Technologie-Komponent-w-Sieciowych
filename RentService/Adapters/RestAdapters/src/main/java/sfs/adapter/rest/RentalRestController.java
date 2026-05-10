package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.Rental;
import sfs.domain.exception.RentalException;
import sfs.ports.api.RentalService;
import sfs.ports.view.dto.CreateRentalRequest;
import sfs.ports.view.RentalViewPort;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalRestController implements RentalViewPort {

    private final RentalService rentalService;

    public RentalRestController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @Override
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @Override
    @GetMapping("/facility/{facilityId}")
    public List<Rental> getRentalsForFacility(@PathVariable String facilityId){
        return rentalService.getRentalsForFacility(facilityId);
    }

    @Override
    @GetMapping("/client/{clientId}")
    public List<Rental> getRentalsForClient(@PathVariable String clientId){
        return rentalService.getRentalsForClient(clientId);
    }

    @Override
    @PostMapping("/rent")
    public Rental rentFacility(@Valid @RequestBody CreateRentalRequest request) throws RentalException {
        return rentalService.rentFacility(request.getClientId(), request.getFacilityId(), request.getStartTime(), request.getEndTime());
    }

    @Override
    @GetMapping("/client/past/{clientId}")
    public List<Rental> getPastRentalsForClient(@PathVariable String clientId) {
        return rentalService.getPastRentalsForClient(clientId);
    }

    @Override
    @GetMapping("/client/current/{clientId}")
    public List<Rental> getCurrentRentalsForClient(@PathVariable String clientId) {
        return rentalService.getCurrentRentalsForClient(clientId);
    }

    @Override
    @GetMapping("/facility/past/{facilityId}")
    public List<Rental> getPastRentalsForFacility(@PathVariable String facilityId) {
        return rentalService.getPastRentalsForFacility(facilityId);
    }

    @Override
    @GetMapping("/facility/current/{facilityId}")
    public List<Rental> getCurrentRentalsForFacility(@PathVariable String facilityId) {
        return rentalService.getCurrentRentalsForFacility(facilityId);
    }

    @Override
    @PutMapping("/finish/{id}")
    public Rental endRental(@PathVariable String id) throws RentalException {
        return rentalService.endRental(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable String id) throws RentalException {
        rentalService.deleteRental(id);
    }
}