package com.example.flightservice.controllers;

import com.example.flightservice.model.Flight;
import com.example.flightservice.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable("id") int flightId) {
        Optional<Flight> flightOptional = flightService.getFlightById(flightId);
        return flightOptional.get();
    }
}
