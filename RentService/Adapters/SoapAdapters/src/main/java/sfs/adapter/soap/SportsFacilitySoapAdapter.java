package sfs.adapter.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import sfs.domain.model.*;
import sfs.ports.api.SportsFacilityService;
import sfs.ports.view.dto.*;
import sfs.ports.view.SportsFacilityViewPort;

import java.util.List;

@Component
@WebService(serviceName = "SportsFacilityService")
public class SportsFacilitySoapAdapter implements SportsFacilityViewPort {

    private final SportsFacilityService sportsFacilityService;

    public SportsFacilitySoapAdapter(SportsFacilityService sportsFacilityService) {
        this.sportsFacilityService = sportsFacilityService;
    }

    @Override
    @WebMethod
    public SportsFacility getFacilityById(String facilityId) throws Exception {
        return sportsFacilityService.getFacilityById(facilityId);
    }

    @Override
    @WebMethod
    public List<SportsFacility> getAllFacilities() {
        return sportsFacilityService.getAllFacilities();
    }

    @Override
    @WebMethod
    public SportsFacility createTennisCourt(CreateTennisCourtRequest request) throws Exception {
        SurfaceType typeEnum = SurfaceType.valueOf(request.getSurfaceType().toUpperCase());
        TennisCourt court = new TennisCourt(request.getName(), request.getPricePerHour(), request.getCapacity(), typeEnum, request.getIsIndoor());
        return sportsFacilityService.createTennisCourt(court);
    }

    @Override
    @WebMethod
    public SportsFacility createGym(CreateGymRequest request) throws Exception {
        Gym gym = new Gym(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getAreaInSqm(), request.getHasSauna());
        return sportsFacilityService.createGym(gym);
    }

    @Override
    @WebMethod
    public SportsFacility createSwimmingPool(CreateSwimmingPoolRequest request) throws Exception {
        SwimmingPool pool = new SwimmingPool(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getPoolLength(), request.getNumberOfLanes());
        return sportsFacilityService.createSwimmingPool(pool);
    }

    @Override
    @WebMethod
    public Gym updateGym(String id, UpdateGymRequest request) throws Exception {
        Gym gym = new Gym(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getAreaInSqm(), request.getHasSauna());
        return sportsFacilityService.updateGym(id, gym);
    }

    @Override
    @WebMethod
    public TennisCourt updateTennisCourt(String id, UpdateTennisCourtRequest request) throws Exception {
        SurfaceType typeEnum = SurfaceType.valueOf(request.getSurfaceType().toUpperCase());
        TennisCourt court = new TennisCourt(request.getName(), request.getPricePerHour(), request.getCapacity(), typeEnum, request.getIsIndoor());
        return sportsFacilityService.updateTennisCourt(id, court);
    }

    @Override
    @WebMethod
    public SwimmingPool updateSwimmingPool(String id, UpdateSwimmingPoolRequest request) throws Exception {
        SwimmingPool pool = new SwimmingPool(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getPoolLength(), request.getNumberOfLanes());
        return sportsFacilityService.updateSwimmingPool(id, pool);
    }

    @Override
    @WebMethod
    public void deleteFacility(String facilityId) throws Exception {
        sportsFacilityService.deleteFacility(facilityId);
    }
}