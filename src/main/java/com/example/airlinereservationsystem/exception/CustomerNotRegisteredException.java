package com.example.airlinereservationsystem.exception;

public class CustomerNotRegisteredException extends RuntimeException {

    public CustomerNotRegisteredException(String message) {
        super(message);
    }
}
