package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserController {

  @Autowired
  private UserRepository userRepo;

  @GetMapping(value = "/{id}")
  public User getUserById(@PathVariable String id) {
    return userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @GetMapping(value = "/{username}")
  public User getUserByName(@PathVariable String username) {
    // System.out.println(id);
    return userRepo
      .findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> updateUser(
    @RequestBody User user,
    @PathVariable String id
  ) {
    Optional<User> original = userRepo.findById(id);
    Map userMap = new HashMap();
    if (!original.isPresent()) {
      userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
      userMap.put("error", "User not found!");
      return ResponseEntity.ok().body(userMap);
    }

    StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
    String result = encoder.encode(user.getPassword());
    user.setPassword(result);

    userMap.put("id", user.getId());
    userMap.put("username", user.getUsername());
    userMap.put("email", user.getEmail());
    userMap.put("firstName", user.getFirstName());
    userMap.put("lastName", user.getLastName());
    userMap.put("friendIDs", user.getFriendIDs());
    userMap.put("announcementIDs", user.getAnnouncementIDs());
    userMap.put("matchIDs", user.getMatchIDs());
    userMap.put("tournament1Id", user.getTournament1Id());
    userMap.put("tournament2Id", user.getTournament2Id());
    userMap.put("team1Id", user.getTeam1Id());
    userMap.put("team2Id", user.getTeam2Id());
    userMap.put("accountPoints", user.getAccountPoint());
    userRepo.save(user);
    return ResponseEntity.ok().body(userMap);
  }
}
