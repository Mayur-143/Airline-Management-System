package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.FlightSeats;
import com.example.AirlineManagementSystem.rowmapper.FlightSeatsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class FlightSeatsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FlightSeatsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(FlightSeats flightSeats) {
        String sql = "INSERT INTO FLIGHT_SEATS (flight_id, available_economy_seats, available_business_seats, available_first_class_seats) " +
                     "VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"flight_seat_id"});
            ps.setInt(1, flightSeats.getFlightId());
            ps.setInt(2, flightSeats.getAvailableEconomySeats());
            ps.setInt(3, flightSeats.getAvailableBusinessSeats());
            ps.setInt(4, flightSeats.getAvailableFirstClassSeats());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // returns generated flight_seat_id
    }

    public FlightSeats findByFlightId(int flightId) {
        String sql = "SELECT * FROM FLIGHT_SEATS WHERE flight_id = ?";
        return jdbcTemplate.queryForObject(sql, new FlightSeatsRowMapper(), flightId);
    }

    public int updateAvailableSeats(int flightId, int economySeats, int businessSeats, int firstClassSeats) {
        String sql = "UPDATE flight_Seats SET available_economy_seats = ?, available_business_seats = ?, available_first_class_seats = ? WHERE flight_id = ?";
        return jdbcTemplate.update(sql, economySeats, businessSeats, firstClassSeats, flightId);
    }

    public int getAvailableSeats(int flightId, String classType) {
        String sql = "";
        // Determine the column to query based on class type
        switch (classType.toLowerCase()) {
            case "economy":
                sql = "SELECT available_economy_seats FROM flight_seats WHERE flight_id = ?";
                break;
            case "business":
                sql = "SELECT available_business_seats FROM flight_seats WHERE flight_id = ?";
                break;
            case "first class":
                sql = "SELECT available_first_class_seats FROM flight_seats WHERE flight_id = ?";
                break;
            default:
                throw new IllegalArgumentException("Invalid class type: " + classType);
        }
    
        // Execute the query and return the available seats
        return jdbcTemplate.queryForObject(sql, new Object[]{flightId}, Integer.class);
    }
    
}
