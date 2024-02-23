package com.example.flightservice.services;

import com.example.flightservice.model.Flight;
import com.example.flightservice.repositories.FlightRepository;
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
