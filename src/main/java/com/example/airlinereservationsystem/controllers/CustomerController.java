package com.example.airlinereservationsystem.controllers;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import com.example.airlinereservationsystem.services.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/health")
    public String health(){
        return healthEndpoint.health().toString();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") int customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);

        return customer.get();
    }

    @GetMapping("/name/{name}")
    public Customer getCustomerByName(@PathVariable("name") String name) {
        return customerService.getCustomerByName(name);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/all/{page}")
    public List<Customer> getAllCustomers(@PathVariable int page) {
        return customerService.getAllCustomersPaginated(page);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

}
