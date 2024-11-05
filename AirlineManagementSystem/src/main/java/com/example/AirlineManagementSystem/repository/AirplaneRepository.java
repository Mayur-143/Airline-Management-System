package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.rowmapper.AirplaneRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(sql, new String[] { "airplane_id" });
        ps.setString(1, airplane.getRegistrationNo());
        ps.setString(2, airplane.getDescription());
        ps.setString(3, airplane.getModel());
        ps.setInt(4, airplane.getTotalSeats());
        ps.setInt(5, airplane.getTotalEconomySeats());
        ps.setInt(6, airplane.getTotalBusinessSeats());
        ps.setInt(7, airplane.getTotalFirstClassSeats());
        return ps;
    }, keyHolder);

    return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
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

    public String findAirplaneNameById(int airplaneId) {
        String sql = "SELECT description FROM airplane WHERE airplane_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, airplaneId);
    }
}
