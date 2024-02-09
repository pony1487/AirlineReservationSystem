package com.example.airlinereservationsystem.controllers;

import com.example.airlinereservationsystem.model.Booking;
import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.services.BookingService;
import com.example.airlinereservationsystem.services.CustomerService;
import com.example.airlinereservationsystem.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public void addBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);
    }


}
