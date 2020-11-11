package com.PenguinGangT2.Backend.controller;

import java.util.Optional;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    // @GetMapping(value = "/{id}")
    // public User getUserById(@PathVariable String id){
    //     // System.out.println(id);
    //     return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
    // }

    @GetMapping(value = "/{username}")
    public User getUserByName(@PathVariable String username){
        // System.out.println(id);
        return userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException());
    }

    @PutMapping(value = "/{username}")
    public User updateUser(@RequestBody User user, @PathVariable String username){
        Optional<User> original = userRepo.findByUsername(username);

        if(!original.isPresent()){
            return userRepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException());
        }
        System.out.println(original);

        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        String result = encoder.encode(user.getPassword());

        user.setPassword(result);
        

        return userRepo.save(user);
    }
}
