package sfs.adapter.aggregates;

import org.springframework.stereotype.Component;
import sfs.adapter.data.RentalEnt;
import sfs.adapter.mappers.RentalMapper;
import sfs.adapter.repository.MongoRentalRepository;
import sfs.domain.model.Rental;
import sfs.ports.infrastructure.RentalRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RentalRepositoryAdapter implements RentalRepositoryPort {

    private final MongoRentalRepository mongoRepository;

    public RentalRepositoryAdapter(MongoRentalRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Rental save(Rental rental) {
        RentalEnt entToSave = RentalMapper.toEntity(rental);
        RentalEnt savedEnt = mongoRepository.save(entToSave);
        return RentalMapper.toDomain(savedEnt);
    }

    @Override
    public Optional<Rental> findById(String id) {
        return mongoRepository.findById(id).map(RentalMapper::toDomain);
    }

    @Override
    public List<Rental> findAll() {
        return mongoRepository.findAll().stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public List<Rental> findByClientId(String clientId) {
        return mongoRepository.findByClientId(clientId).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findByFacilityId(String facilityId) {
        return mongoRepository.findByFacilityId(facilityId).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findPastByClientId(String clientId, LocalDateTime now) {
        return mongoRepository.findByClientIdAndEndTimeBefore(clientId, now).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findCurrentByClientId(String clientId, LocalDateTime now) {
        return mongoRepository.findByClientIdAndEndTimeAfter(clientId, now).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findPastByFacilityId(String facilityId, LocalDateTime now) {
        return mongoRepository.findByFacilityIdAndEndTimeBefore(facilityId, now).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findCurrentByFacilityId(String facilityId, LocalDateTime now) {
        return mongoRepository.findByFacilityIdAndEndTimeAfter(facilityId, now).stream()
                .map(RentalMapper::toDomain)
                .collect(Collectors.toList());
    }
}