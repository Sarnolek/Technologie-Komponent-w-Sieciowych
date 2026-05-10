package sfs.ports.infrastructure;

import sfs.domain.model.SportsFacility;

import java.util.List;
import java.util.Optional;


public interface SportsFacilityRepositoryPort {
    SportsFacility save(SportsFacility sportsFacility);
    Optional<SportsFacility> findById(String id);
    List<SportsFacility> findAll();
    void deleteById(String id);
}
