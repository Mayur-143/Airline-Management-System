package com.example.AirlineManagementSystem.repository;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.rowmapper.FlightDTORowMapper;
import com.example.AirlineManagementSystem.rowmapper.FlightRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository {

    private final JdbcTemplate jdbcTemplate;

    public FlightRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save flight data in the Flights table and return the generated key (flight_id)
    public int save(Flight flight) {
        String sql = "INSERT INTO FLIGHTS (flight_number, departure_time, arrival_time, flight_status, economy_seat_fare, " +
                     "business_seat_fare, first_class_seat_fare, arrival_airport_id, departure_airport_id, airplane_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, flight.getFlightNumber());
            ps.setObject(2, flight.getDepartureTime());
            ps.setObject(3, flight.getArrivalTime());
            ps.setString(4, flight.getFlightStatus());
            ps.setInt(5, flight.getEconomySeatFare());
            ps.setInt(6, flight.getBusinessSeatFare());
            ps.setInt(7, flight.getFirstClassSeatFare());
            ps.setInt(8, flight.getArrivalAirportId());
            ps.setInt(9, flight.getDepartureAirportId());
            ps.setInt(10, flight.getAirplaneId());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        flight.setFlightId(generatedId);
        return generatedId;
    }

    // Update an existing flight record based on the flight_id
    public int update(Flight flight) {
        String sql = "UPDATE FLIGHTS SET flight_number = ?, departure_time = ?, arrival_time = ?, flight_status = ?, " +
                     "economy_seat_fare = ?, business_seat_fare = ?, first_class_seat_fare = ?, " +
                     "arrival_airport_id = ?, departure_airport_id = ?, airplane_id = ? " +
                     "WHERE flight_id = ?";
        return jdbcTemplate.update(sql, flight.getFlightNumber(), flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getFlightStatus(), flight.getEconomySeatFare(), flight.getBusinessSeatFare(),
                flight.getFirstClassSeatFare(), flight.getArrivalAirportId(), flight.getDepartureAirportId(),
                flight.getAirplaneId(), flight.getFlightId());
    }

    // Find a specific flight by its flight_id and return it as a Flight object
    public Optional<Flight> findById(int flightId) {
        String sql = "SELECT * FROM FLIGHTS WHERE flight_id = ?";
        return jdbcTemplate.query(sql, new FlightRowMapper(), flightId)
                           .stream().findFirst();
    }

    // Find all flights and return them as a list of Flight objects
    public List<Flight> findAll() {
        String sql = "SELECT * FROM FLIGHTS";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }

    // Find all flights and return them as a list of FlightDTOs, including airport and airplane names
    public List<FlightDTO> findAllWithDetails() {
        String sql = "SELECT f.*, da.airport_name AS departureAirportName, aa.airport_name AS arrivalAirportName, ap.description AS airplaneName " +
                     "FROM FLIGHTS f " +
                     "JOIN AIRPORT da ON f.departure_airport_id = da.airport_id " +
                     "JOIN AIRPORT aa ON f.arrival_airport_id = aa.airport_id " +
                     "JOIN AIRPLANE ap ON f.airplane_id = ap.airplane_id";

        return jdbcTemplate.query(sql, new FlightDTORowMapper());
    }

    // Delete a flight record by its flight_id
    public int deleteById(int flightId) {
        String sql = "DELETE FROM FLIGHTS WHERE flight_id = ?";
        return jdbcTemplate.update(sql, flightId);
    }

    public List<FlightDTO> findFlightsByCriteria(int departureAirportId, int arrivalAirportId, LocalDateTime startDate) {
        String sql = "SELECT f.flight_id, f.flight_number, f.departure_time, f.arrival_time, f.flight_status, "
                   + "f.economy_seat_fare, f.business_seat_fare, f.first_class_seat_fare, "
                   + "dep.airport_name AS departure_airport, arr.airport_name AS arrival_airport, a.description "
                   + "FROM flights f "
                   + "JOIN airport dep ON f.departure_airport_id = dep.airport_id "
                   + "JOIN airport arr ON f.arrival_airport_id = arr.airport_id "
                   + "JOIN airplane a ON f.airplane_id = a.airplane_id "
                   + "WHERE f.departure_airport_id = ? AND f.arrival_airport_id = ? AND f.departure_time >= ?";
        
        return jdbcTemplate.query(sql, new Object[]{departureAirportId, arrivalAirportId, startDate}, 
            (rs, rowNum) -> {
                FlightDTO flightDTO = new FlightDTO();
                flightDTO.setFlightNumber(rs.getString("flight_number"));
                flightDTO.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                flightDTO.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                flightDTO.setFlightStatus(rs.getString("flight_status"));
                flightDTO.setEconomySeatFare(rs.getInt("economy_seat_fare"));
                flightDTO.setBusinessSeatFare(rs.getInt("business_seat_fare"));
                flightDTO.setFirstClassSeatFare(rs.getInt("first_class_seat_fare"));
                flightDTO.setDepartureAirportName(rs.getString("departure_airport"));
                flightDTO.setArrivalAirportName(rs.getString("arrival_airport"));
                flightDTO.setAirplaneName(rs.getString("description"));
                return flightDTO;
            });
    }
}
