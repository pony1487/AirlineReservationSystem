package com.example.planeservice.controllers;

import com.example.planeservice.services.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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