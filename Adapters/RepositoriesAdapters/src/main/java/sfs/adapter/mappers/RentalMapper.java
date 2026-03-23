package sfs.adapter.mappers;

import sfs.adapter.data.RentalEnt;
import sfs.domain.model.Rental;

public class RentalMapper {

    public static RentalEnt toEntity(Rental rental) {
        if (rental == null) return null;

        RentalEnt ent = new RentalEnt(
                rental.getClientId(),
                rental.getFacilityId(),
                rental.getStartTime(),
                rental.getEndTime()
        );
        ent.setId(rental.getId());
        return ent;
    }

    public static Rental toDomain(RentalEnt ent) {
        if (ent == null) return null;

        Rental rental = new Rental(
                ent.getClientId(),
                ent.getFacilityId(),
                ent.getStartTime(),
                ent.getEndTime()
        );
        rental.setId(ent.getId());
        return rental;
    }
}