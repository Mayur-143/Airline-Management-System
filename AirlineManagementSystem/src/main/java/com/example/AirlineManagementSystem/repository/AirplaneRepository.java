package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.rowmapper.AirplaneRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AirplaneRepository {

    private final JdbcTemplate jdbcTemplate;

    public AirplaneRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save a new Airplane
    public int save(Airplane airplane) {
        String sql = "INSERT INTO AIRPLANE (registration_no, description, model, total_seats, total_economy_seats, " +
                     "total_business_seats, total_first_class_seats) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, airplane.getRegistrationNo(), airplane.getDescription(),
                                   airplane.getModel(), airplane.getTotalSeats(),
                                   airplane.getTotalEconomySeats(), airplane.getTotalBusinessSeats(),
                                   airplane.getTotalFirstClassSeats());
    }

    // Update an existing Airplane
    public int update(Airplane airplane) {
        String sql = "UPDATE AIRPLANE SET registration_no = ?, description = ?, model = ?, total_seats = ?, " +
                     "total_economy_seats = ?, total_business_seats = ?, total_first_class_seats = ? WHERE airplane_id = ?";
        return jdbcTemplate.update(sql, airplane.getRegistrationNo(), airplane.getDescription(), airplane.getModel(),
                                   airplane.getTotalSeats(), airplane.getTotalEconomySeats(),
                                   airplane.getTotalBusinessSeats(), airplane.getTotalFirstClassSeats(),
                                   airplane.getAirplaneId());
    }

    // Find an Airplane by ID
    public Optional<Airplane> findById(int airplaneId) {
        String sql = "SELECT * FROM AIRPLANE WHERE airplane_id = ?";
        return jdbcTemplate.query(sql, new AirplaneRowMapper(), airplaneId)
                           .stream().findFirst();
    }

    // Find all Airplanes
    public List<Airplane> findAll() {
        String sql = "SELECT * FROM AIRPLANE";
        return jdbcTemplate.query(sql, new AirplaneRowMapper());
    }

    // Delete an Airplane by ID
    public int deleteById(int airplaneId) {
        String sql = "DELETE FROM AIRPLANE WHERE airplane_id = ?";
        return jdbcTemplate.update(sql, airplaneId);
    }
}
