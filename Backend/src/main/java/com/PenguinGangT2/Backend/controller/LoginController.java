package com.PenguinGangT2.Backend.controller;

import java.util.*;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;
import com.PenguinGangT2.Backend.service.MyUserDetailsService;
import com.PenguinGangT2.Backend.util.JwtUtil;

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
    public Map login(@RequestParam( name = "email") String email, @RequestParam(name = "password") String password) throws Exception{

        Map returnMap = new HashMap();
        //System.out.println(userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException()));
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());

        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        // StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        // System.out.println(encoder.matches(password, user.getPassword()));
        if(user.getPassword().equals(password)){
            try{
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
            }catch(BadCredentialsException e){
                throw new Exception("Incorrect username or password", e);
            }
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            System.out.println(jwt);
            returnMap.put("JWTToken", jwt);
            returnMap.put("username", user.getUsername());
            returnMap.put("email", user.getEmail());
            returnMap.put("firstName", user.getFirstName());
            returnMap.put("lastName", user.getLastName());
            returnMap.put("friendList", user.getFriends());
            returnMap.put("accountPoints", user.getAccountPoint());
            return returnMap;
        }else{
            returnMap.put("status", 404);
            return returnMap;
        }
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepo.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if(userRepo.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        String result = encoder.encode(user.getPassword());
        //
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        
        user.setPassword(result);
        Collection<String> friends = new ArrayList<>();
        user.setFriends(friends);
        user.setAccountPoints(0);

        userRepo.save(user);

        return ResponseEntity.ok("User registered! Welcome");
    }
}
