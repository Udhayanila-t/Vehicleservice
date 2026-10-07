package com.nilaproject.vehicleservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nilaproject.vehicleservice.entity.User;
import com.nilaproject.vehicleservice.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    // REGISTER
    @PostMapping("/register")
    public String registerUser(@RequestBody User user){

        // 🔥 Check duplicate email
        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null){
            return "Email already exists ❌";
        }

        userRepository.save(user);
        return "Registration Successful ✅";
    }

    // LOGIN 🔐 (FINAL SAFE METHOD)
    @PostMapping("/login")
    public Object login(@RequestBody User user){

        // 🔥 Get user by email
        User u = userRepository.findByEmail(user.getEmail());

        // 🔥 Check password manually
        if(u != null && u.getPassword().equals(user.getPassword())){
            return u;   // success
        }else{
            return "INVALID";  // fail
        }
    }

    // GET USERS
    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}