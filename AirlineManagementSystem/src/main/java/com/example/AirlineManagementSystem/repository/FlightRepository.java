package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.rowmapper.FlightRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository {

    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Flight flight) {
        String sql = "INSERT INTO FLIGHTS (flight_number, departure_time, arrival_time, flight_status, economy_seat_fare, " +
                     "business_seat_fare, first_class_seat_fare, airport_id, airplane_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getFlightStatus(), flight.getEconomySeatFare(), flight.getBusinessSeatFare(),
                flight.getFirstClassSeatFare(), flight.getAirportId(), flight.getAirplaneId());
    }

    public int update(Flight flight) {
        String sql = "UPDATE FLIGHTS SET flight_number = ?, departure_time = ?, arrival_time = ?, flight_status = ?, " +
                     "economy_seat_fare = ?, business_seat_fare = ?, first_class_seat_fare = ?, airport_id = ?, airplane_id = ? " +
                     "WHERE flight_id = ?";
        return jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getFlightStatus(), flight.getEconomySeatFare(), flight.getBusinessSeatFare(),
                flight.getFirstClassSeatFare(), flight.getAirportId(), flight.getAirplaneId(), flight.getFlightId());
    }

    public Optional<Flight> findById(int flightId) {
        String sql = "SELECT * FROM FLIGHTS WHERE flight_id = ?";
        return jdbcTemplate.query(sql, new FlightRowMapper(), flightId)
                           .stream().findFirst();
    }

    public List<Flight> findAll() {
        String sql = "SELECT * FROM FLIGHTS";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }

    public int deleteById(int flightId) {
        String sql = "DELETE FROM FLIGHTS WHERE flight_id = ?";
        return jdbcTemplate.update(sql, flightId);
    }
}
