package com.example.flightservice.controllers;

import com.example.flightservice.model.Flight;
import com.example.flightservice.repositories.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository mockFlightRepository;


    @Test
    public void getFlightTest() throws Exception {
        int flightId = 1;
        String flightDescription = "someDescription";
        String startAirport = "DUB";
        String endAirport = "YYZ";
        int planeId = 1;

        Flight flight = new Flight(flightId, flightDescription, startAirport, endAirport, planeId);
        given(mockFlightRepository.findFlightById(flightId)).willReturn(Optional.of(flight));

        mockMvc.perform(get("/flight/{id}", flightId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId").value(flightId))
                .andExpect(jsonPath("$.flightDescription").value(flightDescription))
                .andExpect(jsonPath("$.startAirport").value(startAirport))
                .andExpect(jsonPath("$.endAirport").value(endAirport))
                .andExpect(jsonPath("$.planeId").value(planeId));

    }

    @Test
    public void getFlightTestNotFound() throws Exception {
        int flightId = 1;
        given(mockFlightRepository.findFlightById(flightId)).willReturn(Optional.empty());

        mockMvc.perform(get("/customer/{id}", flightId))
                .andExpect(status().isNotFound());
    }

}
