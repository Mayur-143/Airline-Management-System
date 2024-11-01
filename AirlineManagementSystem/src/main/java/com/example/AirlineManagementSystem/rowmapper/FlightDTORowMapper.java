package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FlightDTORowMapper implements RowMapper<FlightDTO> {

    @Override
    public FlightDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightId(rs.getInt("flight_id"));
        flightDTO.setFlightNumber(rs.getString("flight_number"));
        flightDTO.setDepartureTime(rs.getObject("departure_time", LocalDateTime.class));
        flightDTO.setArrivalTime(rs.getObject("arrival_time", LocalDateTime.class));
        flightDTO.setFlightStatus(rs.getString("flight_status"));
        flightDTO.setEconomySeatFare(rs.getInt("economy_seat_fare"));
        flightDTO.setBusinessSeatFare(rs.getInt("business_seat_fare"));
        flightDTO.setFirstClassSeatFare(rs.getInt("first_class_seat_fare"));

        // Fetching the airport and airplane names from the result set
        flightDTO.setDepartureAirportName(rs.getString("departureAirportName"));
        flightDTO.setArrivalAirportName(rs.getString("arrivalAirportName"));
        flightDTO.setAirplaneName(rs.getString("airplaneName"));

        return flightDTO;
    }
}
