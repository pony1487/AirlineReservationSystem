package com.example.airlinereservationsystem.exception;

public class CustomerAlreadyBookedException extends RuntimeException {

    public CustomerAlreadyBookedException(String message) {
        super(message);
    }
}
