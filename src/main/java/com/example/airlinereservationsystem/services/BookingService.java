package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.Reporting.BookingMessageProducer;
import com.example.airlinereservationsystem.exception.CustomerAlreadyBookedException;
import com.example.airlinereservationsystem.exception.CustomerNotRegisteredException;
import com.example.airlinereservationsystem.exception.FlightDoesNotExistException;
import com.example.airlinereservationsystem.exception.PlaneIsFullException;
import com.example.airlinereservationsystem.model.Booking;
import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Flight;
import com.example.airlinereservationsystem.repositories.BookingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    private BookingMessageProducer bookingMessageProducer;

    @Autowired
    @Qualifier("topicName")
    private String topicName;

    // TODO move to shared class as this is used in BookingMessageConsumer
    private final String errorMessageHeader = "[FAILED]";
    private final String successMessageHeader = "[SUCCESS]";

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    public Booking addBooking(Booking booking) {
        int customerId = booking.getCustomerId();
        int flightId = booking.getFlightId();

        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        if (customerOptional.isEmpty()) {
            throwExceptionIfCustomerNotRegistered(customerId);
        }

        Optional<Flight> flightOptional = flightService.getFlightById(flightId);
        if (flightOptional.isEmpty()) {
            throwExceptionIfFlightDoesNotExist(flightId);
        }

        Flight flight = flightOptional.get();
        int planeId = flight.getPlaneId();
        if (!planeService.planeHasRoom(planeId)) {
            throwExceptionIfPlaneIsFull(planeId);
        }

        Optional<Booking> bookingOptional = bookingRepository.customerIsAlreadyBookedOnFlight(customerId, flightId);
        if (bookingOptional.isPresent()) {
            throwExceptionIfCustomerIsAlreadyBooked(bookingOptional, customerId, flightId);
        }

        Booking completedBooking = makeBooking(booking, planeId);
        String kafkaMessage = successMessageHeader + " booking " + completedBooking.getBookingId() + " was successful";
        bookingMessageProducer.sendMessage(topicName, kafkaMessage);
        return completedBooking;
    }

    @Transactional
    public Booking makeBooking(Booking booking, int planeId) {
        logger.info("Making booking for Customer: " + booking.getCustomerId() + " on flight: " + booking.getFlightId());
        planeService.incrementSeatsReserved(planeId);

        return bookingRepository.save(booking);
    }

    private void throwExceptionIfCustomerNotRegistered(int customerId) {
        String errorMessage = "Customer: " + customerId + " is not registered.";
        String kafkaMessage = createKafkaMessage(errorMessage);
        logger.error(errorMessage);
        bookingMessageProducer.sendMessage(topicName, kafkaMessage);
        throw new CustomerNotRegisteredException(errorMessage);
    }

    private void throwExceptionIfFlightDoesNotExist(int flightId) {
        String errorMessage = "Flight: " + flightId + " does not exist.";
        String kafkaMessage = createKafkaMessage(errorMessage);
        logger.error(errorMessage);
        bookingMessageProducer.sendMessage(topicName, kafkaMessage);
        throw new FlightDoesNotExistException(errorMessage);
    }

    private void throwExceptionIfPlaneIsFull(int planeId) {
        String errorMessage = "Plane: " + planeId + " is full.";
        String kafkaMessage = createKafkaMessage(errorMessage);
        logger.error(errorMessage);
        bookingMessageProducer.sendMessage(topicName, kafkaMessage);
        throw new PlaneIsFullException(errorMessage);
    }

    private void throwExceptionIfCustomerIsAlreadyBooked(Optional<Booking> bookingOptional, int customerId, int flightId) {
        Booking existingBooking = bookingOptional.get();
        String errorMessage = "Customer: " + customerId + " is already booked on Flight: " + flightId + " with Booking: " + existingBooking.getBookingId();
        String kafkaMessage = createKafkaMessage(errorMessage);
        logger.error(errorMessage);
        bookingMessageProducer.sendMessage(topicName, kafkaMessage);
        throw new CustomerAlreadyBookedException(errorMessage);
    }


    private String createKafkaMessage(String errorMessage) {
        return errorMessageHeader + errorMessage;
    }
}
