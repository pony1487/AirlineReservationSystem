package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.model.Booking;
import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Flight;
import com.example.airlinereservationsystem.repositories.BookingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PlaneService planeService;

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    public Booking addBooking(Booking booking) {
        int customerId = booking.getCustomerId();
        int flightId = booking.getFlightId();

        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        if (customerOptional.isEmpty()) {
            String errorMessage = "Customer: " + customerId + " is not registered.";
            logger.error(errorMessage);
            // TODO better handling.
            throw new IllegalStateException(errorMessage);
        }

        Optional<Flight> flightOptional = flightService.getFlightById(flightId);
        if (flightOptional.isEmpty()) {
            String errorMessage = "Flight: " + flightId + " does not exist.";
            logger.error(errorMessage);
            // TODO better handling.
            throw new IllegalStateException(errorMessage);
        }

        Flight flight = flightOptional.get();
        int planeId = flight.getPlaneId();
        if (!planeService.planeHasRoom(planeId)) {
            String errorMessage = "Plane: " + flightId + " is full.";
            logger.error(errorMessage);
            // TODO better handling.
            throw new IllegalStateException(errorMessage);
        }

        Optional<Booking> bookingOptional = bookingRepository.customerIsAlreadyBookedOnFlight(customerId, flightId);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();
            String errorMessage = "Customer: " + customerId + " is already booked on Flight: " + flightId + " with Booking: " + existingBooking.getBookingId();
            logger.error(errorMessage);
            // TODO better handling.
            throw new IllegalStateException(errorMessage);
        }

        return makeBooking(booking, planeId);
    }

    @Transactional
    public Booking makeBooking(Booking booking, int planeId) {
        logger.info("Making booking for Customer: " + booking.getCustomerId() + " on flight: " + booking.getFlightId());
        planeService.incrementSeatsReserved(planeId);

        return bookingRepository.save(booking);
    }
}
