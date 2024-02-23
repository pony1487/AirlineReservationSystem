package com.example.bookingservice.exception;

public class CustomerAlreadyBookedException extends RuntimeException {

    public CustomerAlreadyBookedException(String message) {
        super(message);
    }
}
