package com.example.AirlineManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Flight;


public interface FlightServiceInterface {
    Optional<Flight> getFlightById(int flightId);
    List<FlightDTO> getAllFlights();  // Add this method
    List<FlightDTO> searchFlights(int departureAirportId, int arrivalAirportId, LocalDateTime startDate);
    Optional<FlightDetailsDTO> getFlightDetailsById(int flightId);
}
