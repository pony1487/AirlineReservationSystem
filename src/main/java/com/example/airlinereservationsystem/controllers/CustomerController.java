package com.example.airlinereservationsystem.controllers;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import com.example.airlinereservationsystem.services.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Used as canary to make sure most basic thing works
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name,
                        HttpServletResponse response) {
        response.addHeader("all", "good");
        return "Hello " + name + " you need beer!";
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") int customerId) {
        return customerService.getCustomer(customerId);
    }

    // TODO, how do the input here correctly?
    @GetMapping("/add/{name}/{email}")
    public void addCustomer(@PathVariable("name") String name, @PathVariable("email") String email) {
        // TODO
    }

}
