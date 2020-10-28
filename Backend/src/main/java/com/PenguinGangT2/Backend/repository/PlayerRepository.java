package com.PenguinGangT2.Backend.repository;

import java.util.Optional;

import com.PenguinGangT2.Backend.models.Player;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String>{
    Optional<Player> findByName(String name);

    Optional<Player> findById(String Id);
}



