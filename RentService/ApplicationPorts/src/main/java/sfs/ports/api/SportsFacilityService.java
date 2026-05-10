package sfs.ports.api;

import sfs.domain.model.Gym;
import sfs.domain.model.SportsFacility;
import sfs.domain.model.SwimmingPool;
import sfs.domain.model.TennisCourt;

import java.util.List;

public interface SportsFacilityService {
    Gym createGym(Gym gym);
    TennisCourt createTennisCourt(TennisCourt court);
    SwimmingPool createSwimmingPool(SwimmingPool pool);

    Gym updateGym(String id, Gym updatedGym);
    TennisCourt updateTennisCourt(String id, TennisCourt updatedCourt);
    SwimmingPool updateSwimmingPool(String id, SwimmingPool updatedPool);

    SportsFacility getFacilityById(String facilityId);
    List<SportsFacility> getAllFacilities();
    void deleteFacility(String facilityId);
}