package com.example.flightservice.services;

import com.example.flightservice.model.Flight;
import com.example.flightservice.repositories.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTests {

    @MockBean
    private FlightRepository mockFlightRepository;

    @Autowired
    private FlightService flightService;

    @Test
    public void noFlightReturnedTest() {
        int someId = 1;
        given(mockFlightRepository.findFlightById(someId)).willReturn(Optional.empty());

        Optional<Flight> res = flightService.getFlightById(someId);

        assertTrue(res.isEmpty());
    }

    @Test
    public void flightReturnedFromRepository() {
        Flight flight = new Flight(1, "SomeFlight", "SomeStartAiport", "someEndAirport", 1);

        given(mockFlightRepository.findFlightById(1)).willReturn(Optional.of(flight));

        Optional<Flight> res = flightService.getFlightById(1);

        assertTrue(res.isPresent());
        Flight flightFromDB = res.get();

        assertEquals(flight.getFlightId(), flightFromDB.getFlightId());
        assertEquals(flight.getFlightDescription(), flightFromDB.getFlightDescription());
        assertEquals(flight.getEndAirport(), flightFromDB.getEndAirport());
        assertEquals(flight.getStartAirport(), flightFromDB.getStartAirport());
        assertEquals(flight.getPlaneId(), flightFromDB.getPlaneId());

    }
}
