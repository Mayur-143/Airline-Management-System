package com.example.AirlineManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AirlineManagementSystem.model.FlightSeats;
import com.example.AirlineManagementSystem.repository.FlightSeatsRepository;

// FlightSeatsService.java
@Service
public class FlightSeatsService {

    @Autowired
    private FlightSeatsRepository flightSeatsRepository;

    public FlightSeats findFlightSeatsByFlightId(int flightId) {
        return flightSeatsRepository.findByFlightId(flightId);
    }

    public void updateAvailableSeats(int flightId, String classType, int totalPassengers) {
        FlightSeats flightSeats = flightSeatsRepository.findByFlightId(flightId);
        
        switch (classType.toLowerCase()) {
            case "economy":
                flightSeats.setAvailableEconomySeats(flightSeats.getAvailableEconomySeats() - totalPassengers);
                break;
            case "business":
                flightSeats.setAvailableBusinessSeats(flightSeats.getAvailableBusinessSeats() - totalPassengers);
                break;
            case "first":
                flightSeats.setAvailableFirstClassSeats(flightSeats.getAvailableFirstClassSeats() - totalPassengers);
                break;
            default:
                throw new IllegalArgumentException("Invalid class type: " + classType);
        }

        // Update the flight seats in the database
        flightSeatsRepository.updateAvailableSeats(
            flightId, 
            flightSeats.getAvailableEconomySeats(),
            flightSeats.getAvailableBusinessSeats(),
            flightSeats.getAvailableFirstClassSeats()
        );
    }
}

