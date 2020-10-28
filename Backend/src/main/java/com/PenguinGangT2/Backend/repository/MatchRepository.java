package com.PenguinGangT2.Backend.repository;

import java.util.Optional;

import com.PenguinGangT2.Backend.models.Match;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match, String>{
    Optional<Match> findById(String Id);
}
