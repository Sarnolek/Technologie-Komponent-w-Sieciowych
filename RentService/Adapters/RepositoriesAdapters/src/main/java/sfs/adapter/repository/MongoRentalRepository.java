package sfs.adapter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sfs.adapter.data.RentalEnt;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MongoRentalRepository extends MongoRepository<RentalEnt, String> {
    List<RentalEnt> findByClientId(String clientId);

    List<RentalEnt> findByFacilityId(String facilityId);

    List<RentalEnt> findByClientIdAndEndTimeBefore(String clientId, LocalDateTime now);

    List<RentalEnt> findByClientIdAndEndTimeAfter(String clientId, LocalDateTime now);

    List<RentalEnt> findByFacilityIdAndEndTimeBefore(String facilityId, LocalDateTime now);

    List<RentalEnt> findByFacilityIdAndEndTimeAfter(String facilityId, LocalDateTime now);
}
//    private final MongoTemplate mongoTemplate;
//
//    public MongoRentalRepository(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public Rental save(Rental rental) {
//        return mongoTemplate.save(rental, "rentals");
//    }
//
//    @Override
//    public Optional<Rental> findById(String id) {
//        return Optional.ofNullable(mongoTemplate.findById(id, Rental.class, "rentals"));
//    }
//
//    @Override
//    public List<Rental> findAll() {
//        return mongoTemplate.findAll(Rental.class, "rentals");
//    }
//
//    @Override
//    public void deleteById(String id) {
//            Query query = new Query(Criteria.where("_id").is(id));
//            mongoTemplate.remove(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findByClientId(String clientId) {
//        Query query = new Query(Criteria.where("clientId").is(clientId));
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findByFacilityId(String facilityId) {
//        Query query = new Query(Criteria.where("facilityId").is(facilityId));
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findPastByClientId(String clientId, LocalDateTime now) {
//        Criteria criteria = Criteria.where("clientId").is(clientId)
//                .and("endTime").ne(null).lt(now);
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findCurrentByClientId(String clientId, LocalDateTime now) {
//        Criteria clientCriteria = Criteria.where("clientId").is(clientId);
//        Criteria timeCriteria = new Criteria().orOperator(
//                Criteria.where("endTime").is(null),
//                Criteria.where("endTime").gt(now)
//        );
//
//        Query query = new Query(clientCriteria.andOperator(timeCriteria));
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findPastByFacilityId(String facilityId, LocalDateTime now) {
//        Criteria criteria = Criteria.where("facilityId").is(facilityId)
//                .and("endTime").ne(null).lt(now);
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }
//
//    @Override
//    public List<Rental> findCurrentByFacilityId(String facilityId, LocalDateTime now) {
//        Criteria facilityCriteria = Criteria.where("facilityId").is(facilityId);
//        Criteria timeCriteria = new Criteria().orOperator(
//                Criteria.where("endTime").is(null),
//                Criteria.where("endTime").gt(now)
//        );
//
//        Query query = new Query(facilityCriteria.andOperator(timeCriteria));
//        return mongoTemplate.find(query, Rental.class, "rentals");
//    }

