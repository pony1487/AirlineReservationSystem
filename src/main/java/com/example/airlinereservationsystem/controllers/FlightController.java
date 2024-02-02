package com.example.airlinereservationsystem.controllers;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Flight;
import com.example.airlinereservationsystem.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable("id") int flightId) {
        return flightService.getFlightById(flightId);
    }
}
