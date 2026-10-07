package com.nilaproject.vehicleservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilaproject.vehicleservice.entity.User;
import com.nilaproject.vehicleservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // ADD USER
    public User addUser(User user){
        return userRepository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // GET USER BY ID
    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    // UPDATE USER
    public User updateUser(Integer id, User userDetails){

        User user = userRepository.findById(id).orElse(null);

        if(user != null){

            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setPhone(userDetails.getPhone());

            return userRepository.save(user);
        }

        return null;
    }

    // DELETE USER
    public String deleteUser(Integer id){

        userRepository.deleteById(id);

        return "User Deleted Successfully";
    }

}