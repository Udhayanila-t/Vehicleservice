package com.nilaproject.vehicleservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.nilaproject.vehicleservice.entity.Vehicle;
import com.nilaproject.vehicleservice.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Create Vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get Vehicle By ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    // Update Vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existing = vehicleRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setOwnerName(vehicle.getOwnerName());
            existing.setModel(vehicle.getModel());
            existing.setBrand(vehicle.getBrand());
            existing.setColor(vehicle.getColor());
            existing.setLicensePlate(vehicle.getLicensePlate());
            return vehicleRepository.save(existing);
        }
        return null;
    }

    // Delete Vehicle
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}