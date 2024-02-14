package com.example.airlinereservationsystem.exception;

public class FlightDoesNotExistException extends RuntimeException {

    public FlightDoesNotExistException(String message) {
        super(message);
    }
}
