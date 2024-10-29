package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FlightRowMapper implements RowMapper<Flight> {

    @Override
    public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        Flight flight = new Flight();
        flight.setFlightId(rs.getInt("flight_id"));
        flight.setFlightNumber(rs.getString("flight_number"));
        flight.setDepartureTime(rs.getObject("departure_time", LocalDateTime.class));
        flight.setArrivalTime(rs.getObject("arrival_time", LocalDateTime.class));
        flight.setFlightStatus(rs.getString("flight_status"));
        flight.setEconomySeatFare(rs.getInt("economy_seat_fare"));
        flight.setBusinessSeatFare(rs.getInt("business_seat_fare"));
        flight.setFirstClassSeatFare(rs.getInt("first_class_seat_fare"));
        flight.setAirportId(rs.getInt("airport_id"));
        flight.setAirplaneId(rs.getInt("airplane_id"));
        return flight;
    }
}
