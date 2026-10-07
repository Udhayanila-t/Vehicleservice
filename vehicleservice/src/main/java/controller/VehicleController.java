package com.nilaproject.vehicleservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.nilaproject.vehicleservice.entity.Vehicle;
import com.nilaproject.vehicleservice.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins="*")   // ⭐ IMPORTANT
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // POST - Create Vehicle
    @PostMapping
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    // GET - All Vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // GET - Vehicle By ID
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    // PUT - Update Vehicle
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    // DELETE - Delete Vehicle
    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle Deleted Successfully";
    }
}