package com.PenguinGangT2.Backend.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match")
public class Match {
    @Id
    private String id;
    
    @NotNull
    private String tournamentId;

    
    private ArrayList<String> teamId;
    
    private Date date = new Date();
    
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTournamentId(){
        return tournamentId;
    }

    public void setTournamentId(String tournamentId){
        this.tournamentId = tournamentId;
    }

    public Collection<String> getTeamId(){
        return teamId;
    }

    public void setTeamId(ArrayList<String> teamId){
        this.teamId = teamId;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

}
