package com.example.AirlineManagementSystem.service;

import com.example.AirlineManagementSystem.model.Airplane;
import com.example.AirlineManagementSystem.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    // Add a new Airplane
    public int addAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    // Update an Airplane
    public int updateAirplane(Airplane airplane) {
        return airplaneRepository.update(airplane);
    }

    // Find an Airplane by ID
    public Optional<Airplane> getAirplaneById(int airplaneId) {
        return airplaneRepository.findById(airplaneId);
    }

    // Find all Airplanes
    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    // Delete an Airplane by ID
    public int deleteAirplane(int airplaneId) {
        return airplaneRepository.deleteById(airplaneId);
    }
}

