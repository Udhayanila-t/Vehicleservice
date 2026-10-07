package com.nilaproject.vehicleservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.nilaproject.vehicleservice.entity.Service;
import com.nilaproject.vehicleservice.repository.ServiceRepository;

@RestController
@CrossOrigin
@RequestMapping("/services")   // base URL fix
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // ✅ Add Service (Duplicate check added)
    @PostMapping
    public Service addService(@RequestBody Service service) {

        List<Service> existingServices = serviceRepository.findAll();

        for(Service s : existingServices){
            if(s.getServiceName().equalsIgnoreCase(service.getServiceName())){
                return s; // already exists -> don't insert again
            }
        }

        return serviceRepository.save(service);
    }

    // ✅ Get All Services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

}