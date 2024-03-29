package com.example.planeservice.services;

import com.example.planeservice.model.Plane;
import com.example.planeservice.repositories.PlaneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    Logger logger = LoggerFactory.getLogger(PlaneService.class);

    public Optional<Plane> getPlane(int planeId){
        return planeRepository.findPlaneById(planeId);
    }
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
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
