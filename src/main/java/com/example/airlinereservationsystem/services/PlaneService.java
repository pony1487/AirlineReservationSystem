package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.model.Plane;
import com.example.airlinereservationsystem.repositories.PlaneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    Logger logger = LoggerFactory.getLogger(PlaneService.class);


    public boolean planeHasRoom(int planeId) {
        logger.info("Checking if plane: " + planeId + " has room");
        Optional<Plane> planeOptional = planeRepository.findPlaneById(planeId);

        if (planeOptional.isPresent()) {
            Plane plane = planeOptional.get();
            logger.info("Plane:" + planeId + " has room");
            return plane.hasRoom();
        } else {
            throw new IllegalStateException("Plane: " + planeId + " does not exist.");
        }
    }

    public void incrementSeatsReserved(int planeId){
        logger.info("Incrementing seats reserved for plane: " + planeId);
        Optional<Plane> planeOptional = planeRepository.findPlaneById(planeId);

        if (planeOptional.isPresent()) {
            Plane plane = planeOptional.get();
            planeRepository.incrementSeatsReserved(planeId);
            logger.info("Plane: " + planeId + " has " + plane.getSeatsReserved() + " reserved");
        } else {
            throw new IllegalStateException("Plane: " + planeId + "does not exist.");
        }
    }

    public void resetSeatsReserved(int planeId){
        logger.info("Resetting seats reserved for plane: " + planeId);
        planeRepository.resetSeatsReserved(planeId);
    }
}
