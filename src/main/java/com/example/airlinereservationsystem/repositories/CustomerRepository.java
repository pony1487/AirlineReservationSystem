package com.example.airlinereservationsystem.repositories;


import com.example.airlinereservationsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.customerId=:id")
    public Customer findCustomerById(int id);


    // JPA magic, it will take the method name and create the query!
    public Customer findCustomerByName(String name);
}
