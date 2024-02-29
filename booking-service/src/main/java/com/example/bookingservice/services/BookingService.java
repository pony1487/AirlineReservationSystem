package com.example.bookingservice.services;

import com.example.bookingservice.exception.CustomerAlreadyBookedException;
import com.example.bookingservice.exception.CustomerNotRegisteredException;
import com.example.bookingservice.exception.FlightDoesNotExistException;
import com.example.bookingservice.exception.PlaneIsFullException;
import com.example.bookingservice.model.Booking;;
import com.example.bookingservice.repositories.BookingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class BookingService {


    // TODO, move to some configuration
    private final String customerServiceEndpoint = "http://localhost:9090/customer";
    private final String flightServiceEndpoint = "http://localhost:9091/flight";
    private final String planeServiceEndpoint = "http://localhost:9092/plane";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookingRepository bookingRepository;

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    public Booking addBooking(Booking booking) {
        int customerId = booking.getCustomerId();
        int flightId = booking.getFlightId();

        if (!customerExists(customerId)) {
            String errorMessage = String.format("Customer: %s is not registered.", customerId);
            throw new CustomerNotRegisteredException(errorMessage);
        }

        Optional<String> optionalFlightBody = getFlightBody(flightId);
        if (optionalFlightBody.isEmpty()) {
            String errorMessage = String.format("Flight: %s does not exist.", flightId);
            throw new FlightDoesNotExistException(errorMessage);
        }
        
        String flightBody = optionalFlightBody.get();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map< String, Object > map = springParser.parseMap(flightBody);
        int planeId = (int) map.get("planeId");

        if (!planeIsFull(planeId)) {
            String errorMessage = String.format("Plane: %s is full", planeId);
            throw new PlaneIsFullException(errorMessage);
        }

        Optional<Booking> bookingOptional = bookingRepository.customerIsAlreadyBookedOnFlight(customerId, flightId);
        if (bookingOptional.isPresent()) {
            String errorMessage = String.format("Customer: %s is already booked on flight: %s", planeId, flightId);
            throw new CustomerAlreadyBookedException(errorMessage);
        }

        return makeBooking(booking, planeId);
    }


    private boolean customerExists(int customerId) {
        String customerEndpoint = String.format("%s/%s", customerServiceEndpoint, customerId);
        try {
            HttpEntity<Integer> entity = new HttpEntity<>(customerId);
            ResponseEntity<String> customerResponse = restTemplate.exchange(customerEndpoint, HttpMethod.HEAD, entity, String.class);
            logger.info("Customer: " + customerId + " is registered, continuing.");
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                logger.error("Customer: " + customerId + " is not registered, continuing.");
                return false;
            }
        }
        return true;
    }

    private Optional<String> getFlightBody(int flightId){
        String flightEndpoint = String.format("%s/%s", flightServiceEndpoint, flightId);
        String flightBody = null;
        try {
            ResponseEntity<String> flightResponse = restTemplate.getForEntity(flightEndpoint, String.class);
            logger.info("Flight: " + flightId + " exists, continuing.");

            flightBody = flightResponse.getBody();
            
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                logger.error("Flight: " + flightId + " does not exist.");
                return Optional.empty();
            }
        }
        return Optional.ofNullable(flightBody).filter(not(String::isEmpty));
    }

    private boolean planeIsFull(int planeId){
        String endpoint = String.format("%s/%s/full", planeServiceEndpoint, planeId);
        ResponseEntity<Boolean> isFullResponse = restTemplate.getForEntity(endpoint, Boolean.class);
        return Boolean.TRUE.equals(isFullResponse.getBody());
    }

    private void incrementSeatsReserved(int planeId){
        String endpoint = String.format("%s/%s/seats", planeServiceEndpoint, planeId);
        restTemplate.put(endpoint, planeId);
    }


    @Transactional
    public Booking makeBooking(Booking booking, int planeId) {
        logger.info("Making booking for Customer: " + booking.getCustomerId() + " on flight: " + booking.getFlightId());
        incrementSeatsReserved(planeId);

        return bookingRepository.save(booking);
    }
}



