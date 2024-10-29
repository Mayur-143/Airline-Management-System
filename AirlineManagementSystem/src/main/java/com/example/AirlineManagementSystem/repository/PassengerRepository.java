package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Passenger;
import com.example.AirlineManagementSystem.rowmapper.PassengerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        return jdbcTemplate.update(sql, 
                passenger.getFirstName(),
                passenger.getMiddleName(),
                passenger.getLastName(),
                passenger.getGender(),
                passenger.getAge(),
                passenger.getAadharNumber(),
                passenger.getBookingId());
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
}

