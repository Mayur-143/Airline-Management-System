package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.dto.BookingDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDetailsRowMapper implements RowMapper<BookingDetailsDTO> {
    @Override
    public BookingDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingDetailsDTO dto = new BookingDetailsDTO();
        dto.setBookingId(rs.getInt("booking_id"));
        dto.setBookingDateTime(rs.getString("bookingDateTime"));
        dto.setStatus(rs.getString("status"));
        dto.setPassengerName(rs.getString("passengerName"));
        dto.setAge(rs.getInt("age"));
        dto.setGender(rs.getString("gender"));
        dto.setFlightNumber(rs.getString("flight_number"));
        dto.setDepartureTime(rs.getString("departure_time"));
        dto.setArrivalTime(rs.getString("arrival_time"));
        dto.setDepartureAirport(rs.getString("departureAirport"));
        dto.setArrivalAirport(rs.getString("arrivalAirport"));
        dto.setSeatFare(rs.getInt("seatFare"));
        dto.setFlightClass(rs.getString("flightClass"));
        return dto;
    }
}
