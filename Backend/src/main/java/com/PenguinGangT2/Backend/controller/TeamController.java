package com.PenguinGangT2.Backend.controller;

import java.util.Collection;

import javax.validation.Valid;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Team;
import com.PenguinGangT2.Backend.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
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
}
