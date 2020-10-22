package com.PenguinGangT2.Backend.controller;

import java.util.Collection;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Player;
import com.PenguinGangT2.Backend.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    
    @Autowired
    private PlayerRepository playerRepo;

    @GetMapping
    public Collection<Player> getAll(){
        return playerRepo.findAll();
    }

    @GetMapping(value = "/{name}")
    public Player getPlayerByName(@PathVariable String name){
        return playerRepo.findByName(name).orElseThrow(()-> new ResourceNotFoundException());
    }
}
