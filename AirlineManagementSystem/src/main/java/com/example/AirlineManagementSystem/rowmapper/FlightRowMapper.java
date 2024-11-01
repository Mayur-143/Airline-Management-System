package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FlightRowMapper implements RowMapper<Flight> {

    // private final boolean includeAirportNames;

    // public FlightRowMapper(boolean includeAirportNames) {
    //     this.includeAirportNames = includeAirportNames;
    // }

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
        flight.setArrivalAirportId(rs.getInt("arrival_airport_id"));
        flight.setDepartureAirportId(rs.getInt("departure_airport_id"));
        flight.setAirplaneId(rs.getInt("airplane_id"));
        // if (includeAirportNames) {
        //     flight.setArrivalAirport(rs.getString("arrival_airport_name"));
        //     flight.setDepartureAirport(rs.getString("departure_airport_name"));
        // }
        String departureAirportName = rs.getString("departureAirportName");
        String arrivalAirportName = rs.getString("arrivalAirportName");
        String airplaneName = rs.getString("airplaneName");

        return flight;
    }
}
