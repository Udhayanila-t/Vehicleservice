package com.nilaproject.vehicleservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nilaproject.vehicleservice.entity.Booking;
import com.nilaproject.vehicleservice.repository.BookingRepository;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    // ✅ ADD BOOKING
    @PostMapping("/add")
    public String addBooking(@RequestBody Booking booking){

        // 🔒 Slot limit (max 2)
        List<Booking> slotBookings =
                bookingRepository.findByBookingDateAndTimeSlot(
                        booking.getBookingDate(),
                        booking.getTimeSlot()
                );

        if(slotBookings.size() >= 2){
            return "❌ Time slot full. Please choose another slot.";
        }

        // 🔥 First-time user check
        List<Booking> allBookings = bookingRepository.findAll();

        long userCount = allBookings.stream()
                .filter(b -> b.getUserName().equals(booking.getUserName()))
                .count();

        boolean isFirstTime = (userCount == 0);

        // 💰 Price calculation
        String[] services = booking.getServiceName().split(",");
        double total = 0;

        for(String s : services){

            s = s.trim();

            if(isFirstTime &&
                    (s.equalsIgnoreCase("Water Wash") ||
                            s.equalsIgnoreCase("Inspection"))){
                continue;
            }
            else if(s.equalsIgnoreCase("Oil Change")){
                total += 500;
            }
            else if(s.equalsIgnoreCase("General Service")){
                total += 1000;
            }
            else if(s.equalsIgnoreCase("Water Wash")){
                total += 300;
            }
            else if(s.equalsIgnoreCase("Inspection")){
                total += 200;
            }
            else{
                total += 300;
            }
        }

        booking.setStatus("Booked");

        bookingRepository.save(booking);

        if(isFirstTime){
            return "✅ Booking Successful! 🎁 Free Service Applied | Total = ₹" + total;
        }else{
            return "✅ Booking Successful | Total = ₹" + total;
        }
    }

    // 📋 GET ALL BOOKINGS
    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    // ❌ CANCEL BOOKING (NEW 🔥)
    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(@PathVariable int id){

        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking != null){

            // already completedனா cancel முடியாது
            if("Completed".equalsIgnoreCase(booking.getStatus())){
                return null;
            }

            booking.setStatus("Cancelled");
            return bookingRepository.save(booking);
        }

        return null;
    }

}