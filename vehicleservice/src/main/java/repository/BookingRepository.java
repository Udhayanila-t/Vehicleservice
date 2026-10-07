package com.nilaproject.vehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nilaproject.vehicleservice.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findByBookingDateAndTimeSlot(String bookingDate, String timeSlot);

}