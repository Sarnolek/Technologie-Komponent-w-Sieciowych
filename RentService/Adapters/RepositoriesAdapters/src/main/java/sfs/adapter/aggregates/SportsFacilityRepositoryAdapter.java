package sfs.adapter.aggregates;

import org.springframework.stereotype.Component;
import sfs.adapter.data.SportsFacilityEnt;
import sfs.adapter.repository.MongoSportsFacilityRepository;
import sfs.adapter.mappers.SportsFacilityMapper;
import sfs.domain.model.SportsFacility;
import sfs.ports.infrastructure.SportsFacilityRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SportsFacilityRepositoryAdapter implements SportsFacilityRepositoryPort {

    private final MongoSportsFacilityRepository mongoRepository;

    public SportsFacilityRepositoryAdapter(MongoSportsFacilityRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public SportsFacility save(SportsFacility facility) {
        SportsFacilityEnt entToSave = SportsFacilityMapper.toEntity(facility);
        SportsFacilityEnt savedEnt = mongoRepository.save(entToSave);
        return SportsFacilityMapper.toDomain(savedEnt);
    }

    @Override
    public Optional<SportsFacility> findById(String id) {
        return mongoRepository.findById(id).map(SportsFacilityMapper::toDomain);
    }

    @Override
    public List<SportsFacility> findAll() {
        return mongoRepository.findAll().stream()
                .map(SportsFacilityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }
}