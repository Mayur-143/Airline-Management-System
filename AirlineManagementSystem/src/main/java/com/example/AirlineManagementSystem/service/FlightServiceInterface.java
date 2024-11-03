package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface FlightServiceInterface {
    Optional<Flight> getFlightById(int flightId);
    List<FlightDTO> getAllFlights();  // Add this method
    List<FlightDTO> searchFlights(int departureAirportId, int arrivalAirportId, LocalDateTime startDate);
    Optional<FlightDetailsDTO> getFlightDetailsById(int flightId);
}
