package com.PenguinGangT2.Backend.models;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team")
public class Team {
    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private Collection<String> playerList;

    private int power;

    private Date date = new Date();

    public String getId(){
        return id; 
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUserId(){
        return userId;
    }

    public void serUserId(String userId){
        this.userId = userId;
    }

    public Collection<String> getPlayerList(){
        return playerList;
    }

    public void setPlayerList(Collection<String> playerList){
        this.playerList = playerList;
    }

    public int getPower(){
        return power;
    }

    public void setPoser(int power){
        this.power = power;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
