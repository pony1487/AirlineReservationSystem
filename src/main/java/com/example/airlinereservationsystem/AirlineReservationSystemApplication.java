package com.example.airlinereservationsystem;

import com.example.airlinereservationsystem.config.ProjectConfig;
import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.model.Plane;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import com.example.airlinereservationsystem.repositories.PlaneRepository;
import com.example.airlinereservationsystem.services.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class AirlineReservationSystemApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            PlaneRepository planeRepository = context.getBean(PlaneRepository.class);
            CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

            List<Plane> planes = planeRepository.getPlanes();
            planes.forEach(System.out::println);

            List<Customer> customers = customerRepository.getAllCustomers();
            customers.forEach(System.out::println);

            CustomerService customerService = context.getBean(CustomerService.class);
            customerService.addOneCustomer();
        }

    }

}
