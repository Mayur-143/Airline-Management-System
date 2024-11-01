package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.rowmapper.AirportRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Repository
public class AirportRepository {

    private final JdbcTemplate jdbcTemplate;

    public AirportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save a new Airport
    public int save(Airport airport) {
        String sql = "INSERT INTO AIRPORT (airport_name, IATA_code, location, address) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        // Use an anonymous class that implements PreparedStatementCreator
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"airport_id"});
            ps.setString(1, airport.getAirportName());
            ps.setString(2, airport.getIATA_code());
            ps.setString(3, airport.getLocation());
            ps.setString(4, airport.getAddress());
            return ps;
        }, keyHolder);
    
        // Retrieve the auto-generated key and set it to the airport object
        airport.setAirportId(keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }

    // Update an existing Airport
    public int update(Airport airport) {
        String sql = "UPDATE AIRPORT SET airport_name = ?, IATA_code = ?, location = ?, address = ? WHERE airport_id = ?";
        return jdbcTemplate.update(sql, airport.getAirportName(), airport.getIATA_code(), airport.getLocation(), airport.getAddress(), airport.getAirportId());
    }

    // Find an Airport by ID
    public Optional<Airport> findById(int airportId) {
        String sql = "SELECT * FROM AIRPORT WHERE airport_id = ?";
        return jdbcTemplate.query(sql, new AirportRowMapper(), airportId)
                           .stream().findFirst();
    }

    // Find all Airports
    public List<Airport> findAll() {
        String sql = "SELECT * FROM AIRPORT";
        return jdbcTemplate.query(sql, new AirportRowMapper());
    }

    // Delete an Airport by ID
    public int deleteById(int airportId) {
        String sql = "DELETE FROM AIRPORT WHERE airport_id = ?";
        return jdbcTemplate.update(sql, airportId);
    }
}
