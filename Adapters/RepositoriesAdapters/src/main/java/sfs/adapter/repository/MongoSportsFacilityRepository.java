package sfs.adapter.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sfs.adapter.data.SportsFacilityEnt;


@Repository
public interface MongoSportsFacilityRepository extends MongoRepository<SportsFacilityEnt, String> {

//    private final MongoTemplate mongoTemplate;
//
//    public MongoSportsFacilityRepository(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public SportsFacility save(SportsFacility sportsFacility) {
//        return mongoTemplate.save(sportsFacility, "facilities");
//    }
//
//    @Override
//    public Optional<SportsFacility> findById(String id) {
//        return Optional.ofNullable(mongoTemplate.findById(id, SportsFacility.class, "facilities"));
//    }
//
//    @Override
//    public List<SportsFacility> findAll() {
//        return mongoTemplate.findAll(SportsFacility.class, "facilities");
//    }
//
//    @Override
//    public void deleteById(String id) {
//        Query query = new Query(Criteria.where("_id").is(id));
//        mongoTemplate.remove(query, SportsFacility.class, "facilities");
//    }
}
