package com.example.AirlineManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AirlineManagementSystem.model.Passenger;
import com.example.AirlineManagementSystem.repository.PassengerRepository;

@Service
public class PassengerService {

    
    private PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public List<Passenger> getPassengersByBookingId(int bookingId) {
        return passengerRepository.findByBookingId(bookingId);
    }

    public List<Passenger> findPassengersByBookingId(int bookingId) {
        return passengerRepository.findPassengersByBookingId(bookingId); // Write the SQL query in the repository
    }
    
}
