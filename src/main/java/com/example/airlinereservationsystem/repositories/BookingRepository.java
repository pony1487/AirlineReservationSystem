package com.example.airlinereservationsystem.repositories;

import com.example.airlinereservationsystem.model.Booking;
import com.example.airlinereservationsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
