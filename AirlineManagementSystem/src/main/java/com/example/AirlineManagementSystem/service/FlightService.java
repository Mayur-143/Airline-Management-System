package com.example.AirlineManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.dto.FlightDetailsDTO;
import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.model.FlightSeats;
import com.example.AirlineManagementSystem.repository.FlightRepository;
import com.example.AirlineManagementSystem.repository.FlightSeatsRepository;

@Service
public class FlightService implements FlightServiceInterface {

    private final FlightRepository flightRepository;
    private final FlightSeatsRepository flightSeatsRepository;
    private final AirplaneService airplaneService;


    @Autowired
    public FlightService(FlightRepository flightRepository, FlightSeatsRepository flightSeatsRepository, AirplaneService airplaneService) {
        this.flightRepository = flightRepository;
        this.flightSeatsRepository = flightSeatsRepository;
        this.airplaneService = airplaneService;
    }

    public int addFlight(Flight flight) {
        // return flightRepository.save(flight);
        // Step 1: Save flight and get generated flight ID
        int flightId = flightRepository.save(flight);

        // Step 2: Retrieve airplane details for seat configurations
        Airplane airplane = airplaneService.getAirplaneById(flight.getAirplaneId())
                        .orElseThrow(() -> new IllegalArgumentException("Airplane not found with ID: " + flight.getAirplaneId()));

        // Step 3: Create FlightSeats object with initial seat availability
        FlightSeats flightSeats = new FlightSeats();
        flightSeats.setFlightId(flightId);
        flightSeats.setAvailableEconomySeats(airplane.getTotalEconomySeats());
        flightSeats.setAvailableBusinessSeats(airplane.getTotalBusinessSeats());
        flightSeats.setAvailableFirstClassSeats(airplane.getTotalFirstClassSeats());

        // Step 4: Save seat availability in FLIGHT_SEATS table
        flightSeatsRepository.save(flightSeats);
        return flightId;
    }

    public int updateFlight(Flight flight) {
        return flightRepository.update(flight);
    }

    public Optional<Flight> getFlightById(int flightId) {
        return flightRepository.findById(flightId);
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAllWithDetails();
    }

    public int deleteFlight(int flightId) {
        return flightRepository.deleteById(flightId);
    }

    @Override
    public List<FlightDTO> searchFlights(int departureAirportId, int arrivalAirportId, LocalDateTime startDate) {
        return flightRepository.findFlightsByCriteria(departureAirportId, arrivalAirportId, startDate);
    }

    public Optional<FlightDetailsDTO> getFlightDetailsById(int flightId) {
        return flightRepository.findFlightDetailsById(flightId);
    }

    public Flight findFlightById(int flightId) {
        return flightRepository.findFlightById(flightId); // Write the SQL query in the repository
    }
    
    // public FlightDetailsDTO getFlightDetailsById(int flightId) {
    //     return flightRepository.getFlightDetailsById(flightId);
    // }
    
    public void updateFlightStatus(int flightId, String flightStatus) {
        flightRepository.updateFlightStatus(flightId, flightStatus);
    }
    
}
