package com.example.AirlineManagementSystem.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.example.AirlineManagementSystem.model.Flights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FlightRowMapper implements RowMapper<Flights> {

    @Override
    public Flights mapRow(ResultSet rs, int rowNum) throws SQLException {
        Flights flight = new Flights();
        
        flight.setFlightId(rs.getInt("flight_id"));
        flight.setFlightNumber(rs.getString("flight_number"));
        flight.setDepartureLocation(rs.getString("departure_location"));
        flight.setArrivalLocation(rs.getString("arrival_location"));
        flight.setDepartureTime(rs.getTimestamp("departure_time")); // Use getTimestamp for DATETIME
        flight.setArrivalTime(rs.getTimestamp("arrival_time"));     // Use getTimestamp for DATETIME
        flight.setDuration(rs.getInt("duration"));
        flight.setAircraftId(rs.getInt("aircraft_id"));
        flight.setStatus(rs.getString("status"));
        flight.setNoOfPassengers(rs.getInt("no_of_passengers"));
        flight.setAirportId(rs.getInt("airport_id"));
        
        return flight;
    }
}

