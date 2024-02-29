package com.example.planeservice.controllers;

import com.example.planeservice.model.Plane;
import com.example.planeservice.repositories.PlaneRepository;
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
public class PlaneControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaneRepository mockPlaneRepository;

    @Test
    public void getPlaneTest() throws Exception {
        int planeId = 1;
        String model = "someModel";
        String engineType = "someType";
        String owner = "someOwner";
        int capacity = 0;
        int seatsReserved = 0;

        Plane plane = new Plane(planeId, model, capacity, engineType, owner, seatsReserved);
        given(mockPlaneRepository.findPlaneById(planeId)).willReturn(Optional.of(plane));

        mockMvc.perform(get("/plane/{id}", planeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planeId").value(planeId))
                .andExpect(jsonPath("$.model").value(model))
                .andExpect(jsonPath("$.engineType").value(engineType))
                .andExpect(jsonPath("$.owner").value(owner))
                .andExpect(jsonPath("$.capacity").value(capacity))
                .andExpect(jsonPath("$.seatsReserved").value(seatsReserved));
    }

    @Test
    public void getPlaneTestNotFound() throws Exception {
        int planeId = 1;
        given(mockPlaneRepository.findPlaneById(planeId)).willReturn(Optional.empty());

        mockMvc.perform(get("/plane/{id}", planeId))
                .andExpect(status().isNotFound());
    }
}
