package com.nilaproject.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nilaproject.vehicleservice.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}