package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /*
    Just a dummy method to test out Transactional
     */
    @Transactional
    public void addOneCustomer() {
        var customer = new Customer("Rian", "rian@example.com");
        customerRepository.addCustomer(customer);
        throw new RuntimeException("Bad Mojo");
    }
}
