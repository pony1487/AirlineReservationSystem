package com.example.airlinereservationsystem.row_mappers;

import com.example.airlinereservationsystem.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("customer_id");
        String name = rs.getString("name");
        String email = rs.getString("email");


        return new Customer(id, name, email);

    }
}