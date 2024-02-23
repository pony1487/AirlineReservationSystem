package com.example.bookingservice.exception;

public class PlaneIsFullException extends RuntimeException {
    public PlaneIsFullException(String message) {
        super(message);
    }
}
