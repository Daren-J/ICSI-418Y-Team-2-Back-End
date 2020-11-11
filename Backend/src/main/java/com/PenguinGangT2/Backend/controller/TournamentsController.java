package com.PenguinGangT2.Backend.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Tournaments;
import com.PenguinGangT2.Backend.repository.TournamentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournaments")
public class TournamentsController {
    @Autowired
    private TournamentsRepository tournamentsRepo;
    
    @GetMapping
    public Collection<Tournaments> getAll(){
        return tournamentsRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tournaments getTournament(@PathVariable String id){
        return tournamentsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
    }

    @PostMapping
    public Tournaments postTournaments(@Valid @RequestBody Tournaments tournaments){
        return tournamentsRepo.save(tournaments);
    }

    @PutMapping(value = "/{id}")
    public Tournaments updateTournaments(@RequestBody Tournaments tournaments, @PathVariable String id){
        Optional<Tournaments> original = tournamentsRepo.findById(id);

        if(!original.isPresent()){
            return tournamentsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        }
        System.out.println(original);

        tournaments.setId(id);

        return tournamentsRepo.save(tournaments);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteTournaments(@PathVariable String id){
        tournamentsRepo.deleteById(id);
    }


}
