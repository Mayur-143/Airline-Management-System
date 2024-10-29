package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Passenger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(rs.getInt("passenger_id"));
        passenger.setFirstName(rs.getString("first_name"));
        passenger.setMiddleName(rs.getString("middle_name"));
        passenger.setLastName(rs.getString("last_name"));
        passenger.setGender(rs.getString("gender"));
        passenger.setAge(rs.getInt("age"));
        passenger.setAadharNumber(rs.getInt("aadhar_number"));
        passenger.setBookingId(rs.getInt("booking_id"));
        return passenger;
    }
}

