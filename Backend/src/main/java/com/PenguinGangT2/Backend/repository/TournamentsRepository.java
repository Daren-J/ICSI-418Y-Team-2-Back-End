package com.PenguinGangT2.Backend.repository;

import java.util.Optional;

import com.PenguinGangT2.Backend.models.Tournaments;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentsRepository extends MongoRepository<Tournaments, String>{
    Optional<Tournaments> findById(String id);
}
