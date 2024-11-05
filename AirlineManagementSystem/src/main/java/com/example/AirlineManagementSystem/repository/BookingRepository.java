package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.rowmapper.BookingRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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

        KeyHolder keyHolder = new GeneratedKeyHolder(); // Create a KeyHolder to hold the generated key

        jdbcTemplate.update(conn -> { // Change the lambda parameter name to 'conn'
            PreparedStatementCreator psc = (connection) -> {
                var ps = connection.prepareStatement(sql, new String[]{"booking_id"}); // Specify the key column
                ps.setTimestamp(1, Timestamp.valueOf(booking.getBookingDate())); // Convert LocalDateTime to Timestamp
                ps.setInt(2, booking.getTotalPassenger()); // Set total_passenger
                ps.setString(3, booking.getStatus()); // Set status
                ps.setInt(4, booking.getUserId()); // Set user_id
                ps.setInt(5, booking.getFlightId()); // Set flight_id
                return ps;
            };
            return psc.createPreparedStatement(conn); // Use 'conn' here
        }, keyHolder);

        // Return the generated booking_id
        return keyHolder.getKey().intValue();
    }

    // Method to find a booking by ID
    public Optional<Booking> findById(int bookingId) {
        String sql = "SELECT * FROM BOOKING WHERE booking_id = ?";
        return jdbcTemplate.query(sql, new BookingRowMapper(), bookingId)
                           .stream()
                           .findFirst();
    }

    public Booking findBookingById(int bookingId) {
        String query = "SELECT * FROM booking WHERE booking_id = ?";
        return jdbcTemplate.queryForObject(query, new BookingRowMapper(), bookingId);
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

    // Method to insert a new booking and return the generated bookingId
    public int createBooking(int flightId, String classType) {
        String sql = "INSERT INTO booking (booking_date, status, flight_id, class_type) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"booking_id"});
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, "pending");
            ps.setInt(3, flightId);
            ps.setString(4, classType);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Return the generated booking_id
    }

    public void updateBookingDetails(int bookingId, int totalPassengers, int userId) {
        String sql = "UPDATE booking SET total_passenger = ?, user_id = ? WHERE booking_id = ?";
        jdbcTemplate.update(sql, totalPassengers, userId, bookingId);
    }

    public void updateTotalPassengers(int bookingId, int totalPassengers) {
        String sql = "UPDATE BOOKING SET total_passenger = ? WHERE booking_id = ?";
        jdbcTemplate.update(sql, totalPassengers, bookingId);
    }

    public void updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE booking SET status = ? WHERE booking_id = ?";
        jdbcTemplate.update(sql, status, bookingId);
    }

    public void updateUserId(int bookingId,int userId){
        String sql = "UPDATE booking SET user_id = ? WHERE booking_id = ?";
        jdbcTemplate.update(sql,userId,bookingId);
    }
    
}

