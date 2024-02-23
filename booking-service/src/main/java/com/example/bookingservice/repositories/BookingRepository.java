package com.example.bookingservice.repositories;

import com.example.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b FROM Booking b WHERE b.customerId=:customerId AND b.flightId=:flightId")
    public Optional<Booking> customerIsAlreadyBookedOnFlight(int customerId, int flightId);
}
