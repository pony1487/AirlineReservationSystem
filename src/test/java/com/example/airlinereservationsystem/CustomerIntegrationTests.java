package com.example.airlinereservationsystem;

import com.example.airlinereservationsystem.model.Customer;
import com.example.airlinereservationsystem.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockCustomerRepository;

    @Test
    @WithMockUser(username="apiUser",roles="ADMIN")
    public void getCustomerTest() throws Exception {
        Customer customer = new Customer(1, "Bill", "bill@example.com");
        given(mockCustomerRepository.findCustomerById(1)).willReturn(customer);

        mockMvc.perform(get("/customer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bill"))
                .andExpect(jsonPath("$.email").value("bill@example.com"));
    }

}
