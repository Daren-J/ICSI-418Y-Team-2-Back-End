package com.PenguinGangT2.Backend.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Team;
import com.PenguinGangT2.Backend.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepo;

    @GetMapping
    public Collection<Team> getAll(){
        return teamRepo.findAll();
    }

    @GetMapping("/getTeam")
    public Team getTeamByUserId(@Valid @RequestParam(name = "userId") String userId){
        return teamRepo.findByUserId(userId).orElseThrow(()-> new ResourceNotFoundException());
    }

    @PostMapping("/createTeam")
    public Team createTeam(@RequestBody Team team){
        return teamRepo.save(team);
    }

    @PutMapping(value = "/{id}")
    public Team updateTournaments(@RequestBody Team team, @PathVariable String id){
        Optional<Team> original = teamRepo.findById(id);

        if(!original.isPresent()){
            return teamRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        }
        System.out.println(original);


        team.setId(id);

        return teamRepo.save(team);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTournaments(@PathVariable String id){
        teamRepo.deleteById(id);
    }
}
