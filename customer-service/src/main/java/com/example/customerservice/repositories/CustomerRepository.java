package com.example.customerservice.repositories;


import com.example.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.customerId=:id")
    public Optional<Customer> findCustomerById(int id);
}
