package com.nilaproject.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nilaproject.vehicleservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    // 🔥 Only this method enough
    User findByEmail(String email);

}