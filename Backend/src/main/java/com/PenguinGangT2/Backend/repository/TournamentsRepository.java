package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.Tournaments;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentsRepository
  extends MongoRepository<Tournaments, String> {
  Optional<Tournaments> findById(String id);
}
