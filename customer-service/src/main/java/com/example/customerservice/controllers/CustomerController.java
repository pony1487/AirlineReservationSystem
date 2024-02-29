package com.example.customerservice.controllers;

import com.example.customerservice.model.Customer;
import com.example.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int customerId) {
        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);

        if (customerOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

}
