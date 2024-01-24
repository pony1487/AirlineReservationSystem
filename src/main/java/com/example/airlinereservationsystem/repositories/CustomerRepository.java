package com.example.airlinereservationsystem.repositories;


import com.example.airlinereservationsystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer VALUES (NULL, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getEmail());
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Customer(rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        });
    }
}
