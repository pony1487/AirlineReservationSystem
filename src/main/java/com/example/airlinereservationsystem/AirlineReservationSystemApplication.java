package com.example.airlinereservationsystem;

import com.example.airlinereservationsystem.config.ProjectConfig;
import com.example.airlinereservationsystem.model.Plane;
import com.example.airlinereservationsystem.repositories.PlaneRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class AirlineReservationSystemApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            PlaneRepository planeRepository = context.getBean(PlaneRepository.class);

            List<Plane> planes = planeRepository.getPlanes();

            planes.forEach(System.out::println);
        }

    }

}
