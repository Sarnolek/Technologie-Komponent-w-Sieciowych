package sfs.services;

import org.springframework.stereotype.Service;
import sfs.domain.exception.ResourceNotFoundException;
import sfs.domain.model.*;
import sfs.ports.api.SportsFacilityService;
import sfs.ports.infrastructure.RentalRepositoryPort;
import sfs.ports.infrastructure.SportsFacilityRepositoryPort;

import java.util.List;

@Service
public class SportsFacilityServiceImpl implements SportsFacilityService {

    private final SportsFacilityRepositoryPort sportsFacilityRepositoryPort;
    private final RentalRepositoryPort rentalRepositoryPort;

    public SportsFacilityServiceImpl(SportsFacilityRepositoryPort sportsFacilityRepositoryPort, RentalRepositoryPort rentalRepositoryPort){
        this.sportsFacilityRepositoryPort = sportsFacilityRepositoryPort;
        this.rentalRepositoryPort = rentalRepositoryPort;
    }

    @Override
    public Gym createGym(Gym gym) {
        return (Gym) sportsFacilityRepositoryPort.save(gym);
    }

    @Override
    public TennisCourt createTennisCourt(TennisCourt court) {
        return (TennisCourt) sportsFacilityRepositoryPort.save(court);
    }

    @Override
    public SwimmingPool createSwimmingPool(SwimmingPool pool) {
        return (SwimmingPool) sportsFacilityRepositoryPort.save(pool);
    }

    @Override
    public Gym updateGym(String id, Gym updatedGym) {
        Gym gym = (Gym) getFacilityById(id);
        gym.setName(updatedGym.getName());
        gym.setPricePerHour(updatedGym.getPricePerHour());
        gym.setCapacity(updatedGym.getCapacity());
        gym.setAreaInSqm(updatedGym.getAreaInSqm());
        gym.setHasSauna(updatedGym.isHasSauna());
        return (Gym) sportsFacilityRepositoryPort.save(gym);
    }

    @Override
    public TennisCourt updateTennisCourt(String id, TennisCourt updatedCourt) {
        TennisCourt court = (TennisCourt) getFacilityById(id);
        court.setName(updatedCourt.getName());
        court.setPricePerHour(updatedCourt.getPricePerHour());
        court.setCapacity(updatedCourt.getCapacity());
        court.setSurfaceType(updatedCourt.getSurfaceType());
        court.setIndoor(updatedCourt.isIndoor());
        return (TennisCourt) sportsFacilityRepositoryPort.save(court);
    }

    @Override
    public SwimmingPool updateSwimmingPool(String id, SwimmingPool updatedPool) {
        SwimmingPool pool = (SwimmingPool) getFacilityById(id);
        pool.setName(updatedPool.getName());
        pool.setPricePerHour(updatedPool.getPricePerHour());
        pool.setCapacity(updatedPool.getCapacity());
        pool.setPoolLength(updatedPool.getPoolLength());
        pool.setNumberOfLanes(updatedPool.getNumberOfLanes());
        return (SwimmingPool) sportsFacilityRepositoryPort.save(pool);
    }

    @Override
    public SportsFacility getFacilityById(String facilityId) {
        return sportsFacilityRepositoryPort.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie udało się znaleźć obiektu o ID: " + facilityId + "."));
    }

    @Override
    public List<SportsFacility> getAllFacilities() {
        return sportsFacilityRepositoryPort.findAll();
    }

    @Override
    public void deleteFacility(String facilityId) {
        if (sportsFacilityRepositoryPort.findById(facilityId).isEmpty()){
            throw new ResourceNotFoundException("Obiekt sportowy o ID: " + facilityId + " nie istnieje, więc nie można go usunąć.");
        }
        if (!rentalRepositoryPort.findByFacilityId(facilityId).isEmpty()){
            throw new ResourceNotFoundException("Nie można usunąć obiektu o ID: " + facilityId + " , ponieważ jest ZAREZERWOWANY.");
        }
        sportsFacilityRepositoryPort.deleteById(facilityId);
    }
}