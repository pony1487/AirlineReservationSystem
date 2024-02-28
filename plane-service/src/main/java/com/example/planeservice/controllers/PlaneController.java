package com.example.planeservice.controllers;

import com.example.planeservice.model.Plane;
import com.example.planeservice.services.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @GetMapping("/{id}")
    public Optional<Plane> getPlane(@PathVariable("id") int planeId) {
        return planeService.getPlane(planeId);
    }

    @GetMapping("/{id}/full")
    public boolean planeIsFull(@PathVariable("id") int planeId) {
        return planeService.planeHasRoom(planeId);
    }

    @PutMapping("/{id}/seats")
    public void incrementReservedSeats(@PathVariable("id") int planeId) {
        planeService.incrementSeatsReserved(planeId);
    }

    @PutMapping("/resetReservedSeats/{planeId}")
    public void resetReservedSeats(@PathVariable int planeId) {
        planeService.resetSeatsReserved(planeId);
    }
}