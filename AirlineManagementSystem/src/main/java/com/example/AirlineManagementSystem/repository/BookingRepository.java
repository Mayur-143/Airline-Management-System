package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.rowmapper.BookingRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to save a new booking
    public int save(Booking booking) {
        String sql = "INSERT INTO BOOKING (booking_date, total_passenger, status, user_id, flight_id) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
                booking.getBookingDate(),
                booking.getTotalPassenger(),
                booking.getStatus(),
                booking.getUserId(),
                booking.getFlightId());
    }

    // Method to find a booking by ID
    public Optional<Booking> findById(int bookingId) {
        String sql = "SELECT * FROM BOOKING WHERE booking_id = ?";
        return jdbcTemplate.query(sql, new BookingRowMapper(), bookingId)
                           .stream()
                           .findFirst();
    }

    // Method to find all bookings
    public List<Booking> findAll() {
        String sql = "SELECT * FROM BOOKING";
        return jdbcTemplate.query(sql, new BookingRowMapper());
    }

    // Method to update a booking
    public int update(Booking booking) {
        String sql = "UPDATE BOOKING SET booking_date = ?, total_passenger = ?, status = ?, user_id = ?, flight_id = ? WHERE booking_id = ?";
        return jdbcTemplate.update(sql,
                booking.getBookingDate(),
                booking.getTotalPassenger(),
                booking.getStatus(),
                booking.getUserId(),
                booking.getFlightId(),
                booking.getBookingId());
    }

    // Method to delete a booking by ID
    public int deleteById(int bookingId) {
        String sql = "DELETE FROM BOOKING WHERE booking_id = ?";
        return jdbcTemplate.update(sql, bookingId);
    }
}

