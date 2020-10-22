package com.PenguinGangT2.Backend.models;

import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(max = 40)
    private String password;

    @NotBlank
    private int accountPoints;

    private Collection<String> friends;

    public String getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public int getAccountPoint(){
        return accountPoints;
    }
    public void setAccountPoints(int accountPoints){
        this.accountPoints = accountPoints;
    }

    public Collection<String> getFriends(){
        return friends;
    }
    public void setFriends(Collection<String> friends){
        this.friends = friends;
    }


    


    
}
