package com.PenguinGangT2.Backend.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "announcement")
public class Announcement {

  private String id;

  private String userId;

  private String title;

  private String description;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescrption(String description) {
    this.description = description;
  }
}
