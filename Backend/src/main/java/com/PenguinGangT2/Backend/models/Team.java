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
  private Collection<String> playerIdList;

  private int power;

  private int totalPoints;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void serUserId(String userId) {
    this.userId = userId;
  }

  public Collection<String> getPlayerIdList() {
    return playerIdList;
  }

  public void setPlayerIdList(Collection<String> playerIdList) {
    this.playerIdList = playerIdList;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public int getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }
}
