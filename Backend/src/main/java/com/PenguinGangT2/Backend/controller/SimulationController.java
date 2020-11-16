package com.PenguinGangT2.Backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Match;
import com.PenguinGangT2.Backend.models.Team;
import com.PenguinGangT2.Backend.repository.MatchRepository;
import com.PenguinGangT2.Backend.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    @Autowired
    private MatchRepository matchRepo;
    @Autowired
    private TeamRepository teamRepo;

    @GetMapping(value = "/{id}")
    public HashMap<String, Integer> getScore(@PathVariable String id){
        Match simulMatch = matchRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        Team teamFirst;
        Team teamSecond;
        int k = 0;

        int team1 = 0;
        int team2 = 0;
        String team1Id = "";
        String team2Id = "";


        Iterator it = simulMatch.getTeamId().iterator();
        while(it.hasNext()){
            // System.out.println(it.next());

            if(k == 0){
                teamFirst = teamRepo.findById((String) it.next()).orElseThrow(()-> new ResourceNotFoundException());
                team1Id = teamFirst.getId();
                team1 = teamFirst.getPower();
                k++;
            }else{
                teamSecond = teamRepo.findById((String) it.next()).orElseThrow(()-> new ResourceNotFoundException());
                team2Id = teamSecond.getId();
                team2 = teamSecond.getPower();
            }
        }

        

        // simulMatch.getTeamId().forEach(e -> {
        //     System.out.println(e);
        //     if(k == 0){
        //         teamFirst = teamRepo.findById(e).orElseThrow(()-> new ResourceNotFoundException());
        //         k++;
        //     }else{
        //         teamSecond = teamRepo.findById(e).orElseThrow(()-> new ResourceNotFoundException());
        //     }
        // });

        
        
        Random random = new Random();
        int team1Score = 0;
        int team2Score = 0;

        for(int i = 0; i < 96; i++){
            int randNum = random.nextInt(team1 + team2);
            System.out.println("rand: " + randNum);
            System.out.println("team1: " + team1);
            System.out.println("team2: " + team2);
            if(randNum + 1> team1){
                // System.out.println("team2 gets point");
                int score = random.nextInt(10);
                if(score > 6){
                    // System.out.println("three point");
                    team2Score += 3;
                }else{
                    // System.out.println("two points");
                    team2Score += 2;
                }
            }else{
                // System.out.println("team1 gets point");
                int score = random.nextInt(10);
                if(score > 6){
                    // System.out.println("three point");
                    team1Score += 3;
                }else{
                    // System.out.println("two points");
                    team1Score += 2;
                }
            }
        }
        if(team1Score == team2Score){
            if(team1 > team2){
                team1Score++;
            }else{
                team2Score++;
            }
        }
        HashMap<String, Integer> finalScore = new HashMap<>();
        finalScore.put(team1Id, team1Score);
        finalScore.put(team2Id, team2Score);
        return finalScore;
    }
}
