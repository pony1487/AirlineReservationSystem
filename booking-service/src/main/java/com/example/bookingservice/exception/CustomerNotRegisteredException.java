package com.example.bookingservice.exception;

public class CustomerNotRegisteredException extends RuntimeException {

    public CustomerNotRegisteredException(String message) {
        super(message);
    }
}
