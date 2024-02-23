package com.example.planeservice.services;

import com.example.planeservice.model.Plane;
import com.example.planeservice.repositories.PlaneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaneServiceTests {

    @MockBean
    private PlaneRepository mockPlaneRepository;

    @Autowired
    private PlaneService planeService;

    @Test
    public void planeHasRoomTest() {
        int someId = 1;

        Plane plane = new Plane(1, "A380", 10,"Jet", "Airbus", 0);
        given(mockPlaneRepository.findPlaneById(someId)).willReturn(Optional.of(plane));

        assertTrue(planeService.planeHasRoom(plane.getPlaneId()));
    }

    @Test
    public void planeHasNoRoomTest() {
        int someId = 1;

        Plane plane = new Plane(1, "A380", 10,"Jet", "Airbus", 10);
        given(mockPlaneRepository.findPlaneById(someId)).willReturn(Optional.of(plane));

        assertFalse(planeService.planeHasRoom(plane.getPlaneId()));
    }

    @Test(expected = IllegalStateException.class)
    public void planeHasRoomPlaneDoesntExistTest() {
        int someId = 1;
        given(mockPlaneRepository.findPlaneById(someId)).willReturn(Optional.empty());

        planeService.planeHasRoom(someId);
    }

    @Test
    public void incrementSeatsReservedTest() {
        int someId = 1;

        Plane plane = new Plane(1, "A380", 10,"Jet", "Airbus", 0);
        given(mockPlaneRepository.findPlaneById(someId)).willReturn(Optional.of(plane));

        planeService.incrementSeatsReserved(someId);
        verify(mockPlaneRepository).incrementSeatsReserved(someId);
    }

    @Test
    public void resetSeatsReservedTest() {
        int someId = 1;
        planeService.resetSeatsReserved(someId);
        verify(mockPlaneRepository).resetSeatsReserved(someId);
    }

}
