package sfs.ports.view;

import sfs.domain.model.*;
import sfs.ports.view.dto.*;

import java.util.List;

public interface SportsFacilityViewPort {
    SportsFacility getFacilityById(String facilityId) throws Exception;
    List<SportsFacility> getAllFacilities();
    SportsFacility createTennisCourt(CreateTennisCourtRequest request) throws Exception;
    SportsFacility createGym(CreateGymRequest request) throws Exception;
    SportsFacility createSwimmingPool(CreateSwimmingPoolRequest request) throws Exception;
    Gym updateGym(String id, UpdateGymRequest request) throws Exception;
    TennisCourt updateTennisCourt(String id, UpdateTennisCourtRequest request) throws Exception;
    SwimmingPool updateSwimmingPool(String id, UpdateSwimmingPoolRequest request) throws Exception;
    void deleteFacility(String facilityId) throws Exception;
}
