package com.nilaproject.vehicleservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nilaproject.vehicleservice.entity.Booking;
import com.nilaproject.vehicleservice.entity.User;
import com.nilaproject.vehicleservice.repository.BookingRepository;
import com.nilaproject.vehicleservice.repository.UserRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    // ✅ Admin Login
    @PostMapping("/login")
    public Object adminLogin(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser != null &&
                existingUser.getPassword().equals(user.getPassword())){

            return existingUser;
        }

        return "INVALID";
    }

    // ✅ View All Bookings
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // 🔥 NEW UPDATE (STATUS + DATE + TIME)
    @PutMapping("/updateBooking/{id}")
    public Booking updateBooking(@PathVariable int id,
                                 @RequestParam String status,
                                 @RequestParam String expectedDate,
                                 @RequestParam String expectedTime) {

        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isPresent()) {

            Booking booking = bookingOptional.get();

            booking.setStatus(status);
            booking.setExpectedDate(expectedDate);
            booking.setExpectedTime(expectedTime);

            return bookingRepository.save(booking);
        }

        return null;
    }
}