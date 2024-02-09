package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Flight;
import com.example.airlinereservationsystem.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Optional<Flight> getFlightById(int flightId) {
        return flightRepository.findFlightById(flightId);
    }
}
