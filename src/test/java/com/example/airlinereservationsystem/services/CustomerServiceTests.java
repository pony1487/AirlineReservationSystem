package com.example.airlinereservationsystem.services;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void noCustomersReturnedTest() {
        given(mockCustomerRepository.findAll()).willReturn(Collections.emptyList());

        List<Customer> res = customerService.getAllCustomers();

        assertTrue(res.isEmpty());
    }

    @Test
    public void customerReturnedFromRepository() {
        Customer customer = new Customer(1, "bob", "bob@example.com");

        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));

        Optional<Customer> res = customerService.getCustomerById(1);

        assertTrue(res.isPresent());
        Customer customerFromDB = res.get();

        assertEquals(customer.getCustomerId(), customerFromDB.getCustomerId());
        assertEquals(customer.getName(), customerFromDB.getName());
        assertEquals(customer.getEmail(), customerFromDB.getEmail());
    }
}
