// package com.example.AirlineManagementSystem.rowmapper;

package com.example.AirlineManagementSystem.rowmapper;

import com.example.AirlineManagementSystem.model.Booking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
        booking.setTotalPassenger(rs.getInt("total_passenger"));
        booking.setStatus(rs.getString("status"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setFlightId(rs.getInt("flight_id"));
        return booking;
    }
}

