package com.example.airlinereservationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CustomerNotRegisteredException.class})
    public ResponseEntity<Object> handleCustomerNotRegisteredException(CustomerNotRegisteredException e) {
        return createBadRequestResponseEntity(e);
    }

    @ExceptionHandler(value = {FlightDoesNotExistException.class})
    public ResponseEntity<Object> handleFlightDoesNotExistException(FlightDoesNotExistException e) {
        return createBadRequestResponseEntity(e);
    }

    @ExceptionHandler(value = {PlaneIsFullException.class})
    public ResponseEntity<Object> handlePlaneIsFullException(PlaneIsFullException e) {
        return createBadRequestResponseEntity(e);
    }

    @ExceptionHandler(value = {CustomerAlreadyBookedException.class})
    public ResponseEntity<Object> handleCustomerAlreadyBookedException(CustomerAlreadyBookedException e) {
        return createBadRequestResponseEntity(e);
    }

    private ResponseEntity<Object> createBadRequestResponseEntity(RuntimeException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }
}
