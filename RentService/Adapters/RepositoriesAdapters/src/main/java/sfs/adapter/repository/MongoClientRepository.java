package sfs.adapter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sfs.adapter.data.ClientEnt;

@Repository
public interface MongoClientRepository extends MongoRepository<ClientEnt, String> {
}