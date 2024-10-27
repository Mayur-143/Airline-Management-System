package com.example.AirlineManagementSystem.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.AirlineManagementSystem.model.Flights;
import com.example.AirlineManagementSystem.rowmapper.FlightRowMapper;

public class FlightRepository {
    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Flights> findAllFlights() {
        String sql = "SELECT * FROM Flights";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }

    // Additional methods for saving, updating, deleting flights can be added here
}
