package com.example.bookingservice.controllers;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
