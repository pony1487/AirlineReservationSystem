package com.example.customerservice.services;

import com.example.customerservice.model.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final int pageSize = 2;

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(int customerId){
        return customerRepository.findCustomerById(customerId);
    }

    public Customer getCustomerByName(String name){
        return customerRepository.findCustomerByName(name);
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll(Sort.by("customerId").descending());
    }

    public List<Customer> getAllCustomersPaginated(int page){
        Sort sortBy = Sort.by("customerId").descending();
        return customerRepository.findAll(PageRequest.of(page, pageSize, sortBy)).getContent();
    }
}
