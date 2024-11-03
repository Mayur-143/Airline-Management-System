package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDetailsDTORowMapper implements RowMapper<FlightDetailsDTO> {

    @Override
    public FlightDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FlightDetailsDTO flightDetails = new FlightDetailsDTO();
        
        flightDetails.setFlightId(rs.getInt("flight_id"));
        flightDetails.setFlightNumber(rs.getString("flight_number"));
        flightDetails.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
        flightDetails.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
        flightDetails.setFlightStatus(rs.getString("flight_status"));
        flightDetails.setEconomySeatFare(rs.getInt("economy_seat_fare"));
        flightDetails.setBusinessSeatFare(rs.getInt("business_seat_fare"));
        flightDetails.setFirstClassSeatFare(rs.getInt("first_class_seat_fare"));
        flightDetails.setDepartureAirportName(rs.getString("departure_airport_name"));
        flightDetails.setArrivalAirportName(rs.getString("arrival_airport_name"));
        flightDetails.setAirplaneName(rs.getString("airplane_name"));
        
        // Set additional airplane fields
        flightDetails.setAirplaneModel(rs.getString("airplane_model"));
        flightDetails.setTotalSeats(rs.getInt("total_seats"));
        flightDetails.setTotalEconomySeats(rs.getInt("total_economy_seats"));
        flightDetails.setTotalBusinessSeats(rs.getInt("total_business_seats"));
        flightDetails.setTotalFirstClassSeats(rs.getInt("total_first_class_seats"));
        flightDetails.setAvailableEconomySeats(rs.getInt("available_economy_seats"));
        flightDetails.setAvailableBusinessSeats(rs.getInt("available_business_seats"));
        flightDetails.setAvailableFirstClassSeats(rs.getInt("available_first_class_seats"));

        
        return flightDetails;
    }
}
