package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.dto.FlightDTO;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements FlightServiceInterface {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public int addFlight(Flight flight) {
        return flightRepository.save(flight);
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
}
