package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.Match;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match, String> {
  Optional<Match> findById(String Id);
  Optional<Match> findByTournamentId(String tournamentId);
  Optional<Match> findByTeamId(String teamId);
  Collection<Match> findAllByTournamentId(String tournamentId);
}
