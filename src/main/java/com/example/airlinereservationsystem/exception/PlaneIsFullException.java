package com.example.airlinereservationsystem.exception;

public class PlaneIsFullException extends RuntimeException {
    public PlaneIsFullException(String message) {
        super(message);
    }
}
