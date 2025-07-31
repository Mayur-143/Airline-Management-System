package com.example.AirlineManagementSystem.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.AirlineManagementSystem.model.Passenger;
import com.example.AirlineManagementSystem.rowmapper.PassengerRowMapper;

@Repository
public class PassengerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PassengerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to save a new passenger
    public int save(Passenger passenger) {
        String sql = "INSERT INTO PASSENGER (first_name, middle_name, last_name, gender, age, aadhar_number, booking_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Use update method that supports keyHolder to capture the generated key
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"passenger_id"});
            ps.setString(1, passenger.getFirstName());
            ps.setString(2, passenger.getMiddleName());
            ps.setString(3, passenger.getLastName());
            ps.setString(4, passenger.getGender());
            ps.setInt(5, passenger.getAge());
            ps.setString(6, passenger.getAadharNumber());
            ps.setInt(7, passenger.getBookingId());
            return ps;
        }, keyHolder);

        // Get the generated passenger ID from the keyHolder
        return keyHolder.getKey().intValue();
    }

    // Method to find a passenger by ID
    public Optional<Passenger> findById(int passengerId) {
        String sql = "SELECT * FROM PASSENGER WHERE passenger_id = ?";
        return jdbcTemplate.query(sql, new PassengerRowMapper(), passengerId)
                           .stream()
                           .findFirst();
    }

    // Method to find all passengers
    public List<Passenger> findAll() {
        String sql = "SELECT * FROM PASSENGER";
        return jdbcTemplate.query(sql, new PassengerRowMapper());
    }

    // Method to update a passenger
    public int update(Passenger passenger) {
        String sql = "UPDATE PASSENGER SET first_name = ?, middle_name = ?, last_name = ?, gender = ?, age = ?, aadhar_number = ?, booking_id = ? WHERE passenger_id = ?";
        return jdbcTemplate.update(sql,
                passenger.getFirstName(),
                passenger.getMiddleName(),
                passenger.getLastName(),
                passenger.getGender(),
                passenger.getAge(),
                passenger.getAadharNumber(),
                passenger.getBookingId(),
                passenger.getPassengerId());
    }

    // Method to delete a passenger by ID
    public int deleteById(int passengerId) {
        String sql = "DELETE FROM PASSENGER WHERE passenger_id = ?";
        return jdbcTemplate.update(sql, passengerId);
    }

    public List<Passenger> findByBookingId(int bookingId) {
        String sql = "SELECT * FROM passenger WHERE booking_id = ?";
        return jdbcTemplate.query(sql, new PassengerRowMapper(), bookingId);
    }

    public List<Passenger> findPassengersByBookingId(int bookingId) {
        String query = "SELECT * FROM passenger WHERE booking_id = ?";
        return jdbcTemplate.query(query, ps -> ps.setInt(1, bookingId), new PassengerRowMapper());
    }
    
}

