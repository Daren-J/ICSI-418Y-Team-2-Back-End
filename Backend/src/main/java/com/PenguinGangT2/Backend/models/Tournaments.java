package com.PenguinGangT2.Backend.models;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournaments")
public class Tournaments {
    
    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    private String tournamentName;

    private Collection<String> registeredUserId;

    @NotNull
    private int participantLimit;

    private Date date = new Date();


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public void setTournametName(String tournamentName){
        this.tournamentName = tournamentName;
    }

    public Collection<String> getRegisteredUserId(){
        return registeredUserId;
    }

    public void setRegisteredTeamId(Collection<String> registeredUserId){
        this.registeredUserId = registeredUserId;
    }

    public int getParticipantLimit(){
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit){
        this.participantLimit = participantLimit;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }


}
