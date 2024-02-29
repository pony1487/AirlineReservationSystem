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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//TODO need to rewrite all of these after moving each spring service to microservice

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTests {

    @MockBean
    private BookingRepository mockBookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RestTemplate restTemplate;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    int customerId = 1;
    int flightId = 1;
    int planeId = 1;
    int bookingId = 1;

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
    @Test
    public void addBookingCustomerNotRegisteredTest() {
        Booking booking = new Booking(bookingId, flightId, customerId);

        String path = String.format("http://localhost:9090/customer/%s", customerId);

        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo(path)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        expectedEx.expect(CustomerNotRegisteredException.class);

        String expectedError = String.format("Customer: %s is not registered.", customerId);
        expectedEx.expectMessage(expectedError);

        bookingService.addBooking(booking);
    }

    @Test
    public void addBookingFlightDoesNotExistTest() {
        Booking booking = new Booking(bookingId, flightId, customerId);

        String customerPath = String.format("http://localhost:9090/customer/%s", customerId);
        String flightPath = String.format("http://localhost:9091/flight/%s", flightId);

        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();

        mockServer.expect(requestTo(customerPath)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK));
        mockServer.expect(requestTo(flightPath)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        expectedEx.expect(FlightDoesNotExistException.class);

        String expectedError = String.format("Flight: %s does not exist.", flightId);
        expectedEx.expectMessage(expectedError);

        bookingService.addBooking(booking);
    }

    @Test
    public void addBookingFlightIsFullTest() {
        Booking booking = new Booking(bookingId, flightId, customerId);

        String customerPath = String.format("http://localhost:9090/customer/%s", customerId);
        String flightPath = String.format("http://localhost:9091/flight/%s", flightId);
        String planePath = String.format("http://localhost:9092/plane/%s/full", planeId);
        String flightBody = "{\"flightId\":1,\"flightDescription\":\"Dublin to Toronto\",\"startAirport\":\"DUB\",\"endAirport\":\"YYZ\",\"planeId\":1}";
        String planeFullBody = "false";

        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();

        mockServer.expect(requestTo(customerPath)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK));
        mockServer.expect(requestTo(flightPath)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .body(flightBody));
        mockServer.expect(requestTo(planePath)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .body(planeFullBody));

        // TODO Plane is full part

        bookingService.addBooking(booking);
    }
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
