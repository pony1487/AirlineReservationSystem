package com.example.airlinereservationsystem.repositories;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE f.flightId=:id")
    public Flight findFlightById(int id);
}
