package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.Airport;
import com.example.AirlineManagementSystem.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // Add a new Airport
    public int addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Update an Airport
    public int updateAirport(Airport airport) {
        return airportRepository.update(airport);
    }

    // Find an Airport by ID
    public Optional<Airport> getAirportById(int airportId) {
        return airportRepository.findById(airportId);
    }

    // Find all Airports
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Delete an Airport by ID
    public int deleteAirport(int airportId) {
        return airportRepository.deleteById(airportId);
    }
}
