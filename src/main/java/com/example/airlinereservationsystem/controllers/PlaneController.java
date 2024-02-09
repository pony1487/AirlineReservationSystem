package com.example.airlinereservationsystem.controllers;

import com.example.airlinereservationsystem.model.Booking;
import com.example.airlinereservationsystem.services.BookingService;
import com.example.airlinereservationsystem.services.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @PostMapping("/resetReservedSeats/{planeId}")
    public void resetReservedSeats(@PathVariable int planeId) {
        planeService.resetSeatsReserved(planeId);
    }
}