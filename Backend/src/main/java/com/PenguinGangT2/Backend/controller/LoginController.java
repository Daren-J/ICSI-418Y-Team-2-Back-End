package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;
import com.PenguinGangT2.Backend.service.MyUserDetailsService;
import com.PenguinGangT2.Backend.util.JwtUtil;
import java.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @PostMapping("/signin")
  public ResponseEntity<?> login(
    @RequestParam(name = "email") String email,
    @RequestParam(name = "password") String password
  )
    throws Exception, BadCredentialsException {
    Map returnMap = new HashMap();
    Map userMap = new HashMap();
    User user;

    if (userRepo.existsByEmail(email)) {
      user =
        userRepo
          .findByEmail(email)
          .orElseThrow(() -> new ResourceNotFoundException());

      if (user.getPassword().equals(password)) {
        try {
          authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
              user.getUsername(),
              password
            )
          );
        } catch (BadCredentialsException e) {
          throw new Exception("Could not Authenticate!", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
          user.getUsername()
        );
        final String jwt = jwtUtil.generateToken(userDetails);
        returnMap.put("JWTToken", jwt);
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
        returnMap.put("user", userMap);
        return ResponseEntity.ok().body(returnMap);
      } else {
        returnMap.put("error", "Incorrect password");
        return ResponseEntity.ok().body(returnMap);
      }
    } else {
      returnMap.put("error", "There is no account with that email Address!");
      return ResponseEntity.ok().body(returnMap);
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    Map hashmap = new HashMap();

    if (userRepo.existsByUsername(user.getUsername())) {
      hashmap.put("error", "Username is already taken!");
      return ResponseEntity.ok().body(hashmap);
    }

    if (userRepo.existsByEmail(user.getEmail())) {
      hashmap.put("error", "Email is already taken!");
      return ResponseEntity.ok().body(hashmap);
    }

    String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random random = new Random();
    while (salt.length() < 16) {
      int index = (int) (random.nextFloat() * saltChars.length());
      salt.append(saltChars.charAt(index));
    }
    String userId = salt.toString();

    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
    String result = encoder.encode(user.getPassword());

    Collection<String> friendIDs = new ArrayList<>();
    Collection<String> matchIDs = new ArrayList<>();
    Collection<String> announcementIDs = new ArrayList<>();

    user.setId(userId);
    user.setProfileImageLink("none");
    user.setPassword(result);
    user.setFriendIDs(friendIDs);
    user.setAnnouncementIDs(announcementIDs);
    user.setMatchIDs(matchIDs);
    user.setTournament1Id("none");
    user.setTournament2Id("none");
    user.setTeam1Id("none");
    user.setTeam2Id("none");
    user.setAccountPoints(0);

    userRepo.save(user);
    hashmap.put("message", "Account has been created!");
    return ResponseEntity.ok().body(hashmap);
  }
}
