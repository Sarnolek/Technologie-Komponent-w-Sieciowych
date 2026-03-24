package sfs.adapter.mappers;

import sfs.adapter.data.GymEnt;
import sfs.adapter.data.SportsFacilityEnt;
import sfs.adapter.data.SurfaceTypeEnt;
import sfs.adapter.data.SwimmingPoolEnt;
import sfs.adapter.data.TennisCourtEnt;
import sfs.domain.model.Gym;
import sfs.domain.model.SportsFacility;
import sfs.domain.model.SurfaceType;
import sfs.domain.model.SwimmingPool;
import sfs.domain.model.TennisCourt;

public class SportsFacilityMapper {

    public static SportsFacilityEnt toEntity(SportsFacility facility) {
        if (facility == null) return null;

        SportsFacilityEnt ent;

        if (facility instanceof TennisCourt court) {
            SurfaceTypeEnt surfaceTypeEnt = court.getSurfaceType() != null ?
                    SurfaceTypeEnt.valueOf(court.getSurfaceType().name()) : null;

            ent = new TennisCourtEnt(
                    court.getName(),
                    court.getPricePerHour(),
                    court.getCapacity(),
                    surfaceTypeEnt,
                    court.isIndoor()
            );
        } else if (facility instanceof SwimmingPool pool) {
            ent = new SwimmingPoolEnt(
                    pool.getName(),
                    pool.getPricePerHour(),
                    pool.getCapacity(),
                    pool.getPoolLength(),
                    pool.getNumberOfLanes()
            );
        } else if (facility instanceof Gym gym) {
            ent = new GymEnt(
                    gym.getName(),
                    gym.getPricePerHour(),
                    gym.getCapacity(),
                    gym.getAreaInSqm(),
                    gym.isHasSauna()
            );
        } else {
            throw new IllegalArgumentException("Nieznany obiekt sportowy: " + facility.getClass());
        }

        ent.setId(facility.getId());
        return ent;
    }

    public static SportsFacility toDomain(SportsFacilityEnt ent) {
        if (ent == null) return null;

        SportsFacility facility;

        if (ent instanceof TennisCourtEnt courtEnt) {
            SurfaceType surfaceType = courtEnt.getSurfaceType() != null ?
                    SurfaceType.valueOf(courtEnt.getSurfaceType().name()) : null;

            facility = new TennisCourt(
                    courtEnt.getName(),
                    courtEnt.getPricePerHour(),
                    courtEnt.getCapacity(),
                    surfaceType,
                    courtEnt.isIndoor()
            );
        } else if (ent instanceof SwimmingPoolEnt poolEnt) {
            facility = new SwimmingPool(
                    poolEnt.getName(),
                    poolEnt.getPricePerHour(),
                    poolEnt.getCapacity(),
                    poolEnt.getPoolLength(),
                    poolEnt.getNumberOfLanes()
            );
        } else if (ent instanceof GymEnt gymEnt) {
            facility = new Gym(
                    gymEnt.getName(),
                    gymEnt.getPricePerHour(),
                    gymEnt.getCapacity(),
                    gymEnt.getAreaInSqm(),
                    gymEnt.isHasSauna()
            );
        } else {
            throw new IllegalArgumentException("Nieznana encja obiektu sportowego: " + ent.getClass());
        }

        facility.setId(ent.getId());
        return facility;
    }
}