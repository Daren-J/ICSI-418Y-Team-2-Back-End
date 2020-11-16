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

  private String hostUserId;

  private String hostUsername;

  private boolean lockedInStatus;

  private boolean publicStatus;

  @NotNull
  @Size(max = 100)
  private String tournamentName;

  private Collection<String> registeredTeamId;

  @NotNull
  private int participantLimit;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHostUserId() {
    return hostUserId;
  }

  public void setHostUserId(String hostUserId) {
    this.hostUserId = hostUserId;
  }

  public String getHostUsername() {
    return hostUsername;
  }

  public void setHostUsername(String hostUsername) {
    this.hostUsername = hostUsername;
  }

  public boolean getLockedInStatus() {
    return lockedInStatus;
  }

  public void setLockedInStatus(boolean lockedInStatus) {
    this.lockedInStatus = lockedInStatus;
  }

  public boolean getPublicStatus() {
    return publicStatus;
  }

  public void setPublicStatus(boolean publicStatus) {
    this.publicStatus = publicStatus;
  }

  public String getTournamentName() {
    return tournamentName;
  }

  public void setTournametName(String tournamentName) {
    this.tournamentName = tournamentName;
  }

  public Collection<String> getRegisteredTeamId() {
    return registeredTeamId;
  }

  public void setRegisteredTeamId(Collection<String> registeredTeamId) {
    this.registeredTeamId = registeredTeamId;
  }

  public int getParticipantLimit() {
    return participantLimit;
  }

  public void setParticipantLimit(int participantLimit) {
    this.participantLimit = participantLimit;
  }
}
