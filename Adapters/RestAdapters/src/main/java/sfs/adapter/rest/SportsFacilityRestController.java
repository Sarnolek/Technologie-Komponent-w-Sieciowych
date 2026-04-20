package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.*;
import sfs.ports.api.SportsFacilityService;
import sfs.ports.view.SportsFacilityViewPort;
import sfs.ports.view.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facilities")
public class SportsFacilityRestController implements SportsFacilityViewPort {

    private final SportsFacilityService sportsFacilityService;

    public SportsFacilityRestController(SportsFacilityService sportsFacilityService) {
        this.sportsFacilityService = sportsFacilityService;
    }

    @Override
    @GetMapping("/{facilityId}")
    public SportsFacility getFacilityById(@PathVariable String facilityId) throws Exception {
        return sportsFacilityService.getFacilityById(facilityId);
    }

    @Override
    @GetMapping()
    public List<SportsFacility> getAllFacilities() {
        return sportsFacilityService.getAllFacilities();
    }

    @Override
    @PostMapping("/tennis-courts")
    public SportsFacility createTennisCourt(@Valid @RequestBody CreateTennisCourtRequest request) throws Exception {
        SurfaceType typeEnum = parseSurfaceType(request.getSurfaceType());
        TennisCourt court = new TennisCourt(request.getName(), request.getPricePerHour(), request.getCapacity(), typeEnum, request.getIsIndoor());
        return sportsFacilityService.createTennisCourt(court);
    }

    @Override
    @PostMapping("/gyms")
    public SportsFacility createGym(@Valid @RequestBody CreateGymRequest request) throws Exception {
        Gym gym = new Gym(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getAreaInSqm(), request.getHasSauna());
        return sportsFacilityService.createGym(gym);
    }

    @Override
    @PostMapping("/swimming-pools")
    public SportsFacility createSwimmingPool(@Valid @RequestBody CreateSwimmingPoolRequest request) throws Exception {
        SwimmingPool pool = new SwimmingPool(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getPoolLength(), request.getNumberOfLanes());
        return sportsFacilityService.createSwimmingPool(pool);
    }

    @Override
    @PutMapping("/gyms/{id}")
    public Gym updateGym(@PathVariable String id, @Valid @RequestBody UpdateGymRequest request) throws Exception {
        Gym gym = new Gym(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getAreaInSqm(), request.getHasSauna());
        return sportsFacilityService.updateGym(id, gym);
    }

    @Override
    @PutMapping("/tennis-courts/{id}")
    public TennisCourt updateTennisCourt(@PathVariable String id, @Valid @RequestBody UpdateTennisCourtRequest request) throws Exception {
        SurfaceType typeEnum = parseSurfaceType(request.getSurfaceType());
        TennisCourt court = new TennisCourt(request.getName(), request.getPricePerHour(), request.getCapacity(), typeEnum, request.getIsIndoor());
        return sportsFacilityService.updateTennisCourt(id, court);
    }

    @Override
    @PutMapping("/swimming-pools/{id}")
    public SwimmingPool updateSwimmingPool(@PathVariable String id, @Valid @RequestBody UpdateSwimmingPoolRequest request) throws Exception {
        SwimmingPool pool = new SwimmingPool(request.getName(), request.getPricePerHour(), request.getCapacity(), request.getPoolLength(), request.getNumberOfLanes());
        return sportsFacilityService.updateSwimmingPool(id, pool);
    }

    @Override
    @DeleteMapping("/{facilityId}")
    public void deleteFacility(@PathVariable String facilityId) throws Exception {
        sportsFacilityService.deleteFacility(facilityId);
    }

    private SurfaceType parseSurfaceType(String type) throws Exception {
        try {
            return SurfaceType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Niepoprawny typ nawierzchni: " + type);
        }
    }
}