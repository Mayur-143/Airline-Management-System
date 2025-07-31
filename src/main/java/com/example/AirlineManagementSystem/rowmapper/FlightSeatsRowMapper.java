package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.FlightSeats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightSeatsRowMapper implements RowMapper<FlightSeats> {
    @Override
    public FlightSeats mapRow(ResultSet rs, int rowNum) throws SQLException {
        FlightSeats flightSeats = new FlightSeats();
        flightSeats.setFlightSeatId(rs.getInt("flight_seat_id"));
        flightSeats.setFlightId(rs.getInt("flight_id"));
        flightSeats.setAvailableEconomySeats(rs.getInt("available_economy_seats"));
        flightSeats.setAvailableBusinessSeats(rs.getInt("available_business_seats"));
        flightSeats.setAvailableFirstClassSeats(rs.getInt("available_first_class_seats"));
        return flightSeats;
    }
}
