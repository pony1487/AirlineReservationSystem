package com.example.bookingservice.services;

import com.example.bookingservice.exception.CustomerAlreadyBookedException;
import com.example.bookingservice.exception.CustomerNotRegisteredException;
import com.example.bookingservice.exception.FlightDoesNotExistException;
import com.example.bookingservice.exception.PlaneIsFullException;
import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repositories.BookingRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

//TODO need to rewrite all of these after moving each spring service to microservice

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTests {

//    @MockBean
//    private BookingRepository mockBookingRepository;
//
//    @MockBean
//    private FlightRepository mockFlightRepository;
//
//    @MockBean
//    private CustomerRepository mockCustomerRepository;
//
//    @MockBean
//    private PlaneRepository mockPlaneRepository;
//
//    @Autowired
//    private BookingService bookingService;
//
//    @Rule
//    public ExpectedException expectedEx = ExpectedException.none();
//
//    @Test
//    public void addBookingTest() {
//        int customerId = 1;
//        int flightId = 1;
//        int planeId = 1;
//        int bookingId = 1;
//
//        Customer customer = new Customer(customerId, "bob", "bob@example.com");
//        Flight flight = new Flight(flightId, "SomeFlight", "SomeStartAiport", "someEndAirport", planeId);
//        Plane plane = new Plane(planeId, "A380", 10, "Jet", "Airbus", 0);
//        Booking booking = new Booking(bookingId, flightId, customerId);
//
//        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));
//        given(mockFlightRepository.findFlightById(flightId)).willReturn(Optional.of(flight));
//        given(mockPlaneRepository.findPlaneById(planeId)).willReturn(Optional.of(plane));
//        given(mockBookingRepository.save(booking)).willReturn(booking);
//
//
//        Booking savedBooking = bookingService.addBooking(booking);
//
//        verify(mockBookingRepository).save(booking);
//        verify(mockPlaneRepository).incrementSeatsReserved(plane.getPlaneId());
//
//        assertEquals(booking.getBookingId(), savedBooking.getBookingId());
//        assertEquals(booking.getFlightId(), savedBooking.getFlightId());
//        assertEquals(booking.getCustomerId(), savedBooking.getCustomerId());
//    }
//
//    @Test
//    public void addBookingCustomerNotRegisteredTest() {
//        int customerId = 1;
//        int flightId = 1;
//        int bookingId = 1;
//        Booking booking = new Booking(bookingId, flightId, customerId);
//
//        expectedEx.expect(CustomerNotRegisteredException.class);
//        expectedEx.expectMessage("Customer: " + customerId + " is not registered.");
//
//        bookingService.addBooking(booking);
//    }
//
//    @Test
//    public void addBookingFlightDoesNotExistTest() {
//        int customerId = 1;
//        int flightId = 1;
//        int bookingId = 1;
//
//        Customer customer = new Customer(customerId, "bob", "bob@example.com");
//        Booking booking = new Booking(bookingId, flightId, customerId);
//
//        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));
//
//
//        expectedEx.expect(FlightDoesNotExistException.class);
//        expectedEx.expectMessage("Flight: " + flightId + " does not exist.");
//        bookingService.addBooking(booking);
//    }
//
//    @Test
//    public void addBookingFlightIsFullTest() {
//        int customerId = 1;
//        int flightId = 1;
//        int planeId = 1;
//        int bookingId = 1;
//
//        Customer customer = new Customer(customerId, "bob", "bob@example.com");
//        Flight flight = new Flight(flightId, "SomeFlight", "SomeStartAiport", "someEndAirport", planeId);
//        Plane plane = new Plane(planeId, "A380", 10, "Jet", "Airbus", 10);
//        Booking booking = new Booking(bookingId, flightId, customerId);
//
//        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));
//        given(mockFlightRepository.findFlightById(flightId)).willReturn(Optional.of(flight));
//        given(mockPlaneRepository.findPlaneById(planeId)).willReturn(Optional.of(plane));
//
//        expectedEx.expect(PlaneIsFullException.class);
//        expectedEx.expectMessage("Plane: " + planeId + " is full.");
//
//        bookingService.addBooking(booking);
//    }
//
//    @Test
//    public void customerIsAlreadyBookedTest() {
//        int customerId = 1;
//        int flightId = 1;
//        int planeId = 1;
//        int bookingId = 1;
//
//        Customer customer = new Customer(customerId, "bob", "bob@example.com");
//        Flight flight = new Flight(flightId, "SomeFlight", "SomeStartAiport", "someEndAirport", planeId);
//        Plane plane = new Plane(planeId, "A380", 10, "Jet", "Airbus", 0);
//        Booking booking = new Booking(bookingId, flightId, customerId);
//
//        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));
//        given(mockFlightRepository.findFlightById(flightId)).willReturn(Optional.of(flight));
//        given(mockPlaneRepository.findPlaneById(planeId)).willReturn(Optional.of(plane));
//        given(mockBookingRepository.customerIsAlreadyBookedOnFlight(customerId, flightId)).willReturn(Optional.of(booking));
//
//        expectedEx.expect(CustomerAlreadyBookedException.class);
//        expectedEx.expectMessage("Customer: " + customerId + " is already booked on Flight: " + flightId + " with Booking: " + bookingId);
//
//        bookingService.addBooking(booking);
//    }
}
