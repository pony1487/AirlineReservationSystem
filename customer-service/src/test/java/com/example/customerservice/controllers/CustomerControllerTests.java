package com.example.customerservice.controllers;

import com.example.customerservice.model.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @Test
    public void getCustomerTest() throws Exception {
        int id = 1;
        Customer customer = new Customer(id, "Bill", "bill@example.com");
        given(mockCustomerRepository.findCustomerById(1)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/customer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bill"))
                .andExpect(jsonPath("$.email").value("bill@example.com"));
    }

    @Test
    public void getCustomerTestNotFound() throws Exception {
        int id = 1;
        given(mockCustomerRepository.findCustomerById(id)).willReturn(Optional.empty());

        mockMvc.perform(get("/customer/{id}", id))
                .andExpect(status().isNotFound());
    }

}
