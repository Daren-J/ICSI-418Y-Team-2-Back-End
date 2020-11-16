package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Announcement;
import com.PenguinGangT2.Backend.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

  @Autowired
  private AnnouncementRepository announcementRepo;

  @GetMapping(value = "/{id}")
  public Announcement getAnnouncementById(@PathVariable String id) {
    return announcementRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @GetMapping(value = "/{title}")
  public Announcement getAnnouncementByTitle(@PathVariable String title) {
    return announcementRepo
      .findByTitle(title)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @PostMapping("/set")
  public Announcement setAnnouncement(Announcement announcement) {
    return announcementRepo.save(announcement);
  }

  @GetMapping(value = "/get/{userId}")
  public Announcement getAnnouncementByUserId(@PathVariable String userId) {
    return announcementRepo
      .findByUserId(userId)
      .orElseThrow(() -> new ResourceNotFoundException());
  }
}
