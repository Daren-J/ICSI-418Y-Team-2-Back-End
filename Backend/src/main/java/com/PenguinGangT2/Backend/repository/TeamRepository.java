package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.Team;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
  Optional<Team> findByUserId(String userId);
}
