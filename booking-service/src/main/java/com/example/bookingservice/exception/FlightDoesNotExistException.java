package com.example.bookingservice.exception;

public class FlightDoesNotExistException extends RuntimeException {

    public FlightDoesNotExistException(String message) {
        super(message);
    }
}
