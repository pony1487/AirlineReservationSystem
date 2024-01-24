package com.example.airlinereservationsystem.repositories;

import com.example.airlinereservationsystem.model.JetPlane;
import com.example.airlinereservationsystem.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlaneRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plane> getPlanes() {
        String sql = "SELECT * FROM plane";
        return jdbcTemplate.query(sql, new RowMapper<Plane>() {
            @Override
            public Plane mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new JetPlane(rs.getInt("idplane"),
                        rs.getString("model"),
                        rs.getInt("capacity"),
                        rs.getString("engine_type"),
                        rs.getString("owner"));
            }
        });
    }
}
