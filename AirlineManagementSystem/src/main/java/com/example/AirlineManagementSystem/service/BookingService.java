package com.example.AirlineManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AirlineManagementSystem.dto.BookingDetailsDTO;
import com.example.AirlineManagementSystem.model.Booking;
import com.example.AirlineManagementSystem.model.Flight;
import com.example.AirlineManagementSystem.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Method to handle booking creation
    public int createBooking(int flightId, String classType) {
        return bookingRepository.createBooking(flightId, classType);
    }

    public void updateBookingDetails(int bookingId, int totalPassengers, int userId) {
        bookingRepository.updateBookingDetails(bookingId, totalPassengers, userId);
    }

    public void updateTotalPassengers(int bookingId, int totalPassengers) {
        bookingRepository.updateTotalPassengers(bookingId, totalPassengers);
    }

    // public void updateBookingDetails(int bookingId, int totalPassengers, int userId) {
    //     bookingRepository.updateBookingWithUserAndPassengerCount(bookingId, totalPassengers, userId);
    // }
    public Optional<Booking> getBookingById(int bookingId){
        return bookingRepository.findById(bookingId);
    }

    public Booking findBookingById(int bookingId) {
        return bookingRepository.findBookingById(bookingId); // Write the SQL query in the repository
    }

    public void updateBookingStatus(int bookingId, String status) {
        bookingRepository.updateBookingStatus(bookingId, status);
    }

    public void updateUserId(int bookingId,int userId){
        bookingRepository.updateUserId(bookingId,userId);
    }

    public List<BookingDetailsDTO> getBookingsByUserId(int userId) {
        return bookingRepository.findBookingsByUserId(userId);
    }
    
    public List<BookingDetailsDTO> getBookingsDetails(){
        return bookingRepository.findBookingDetails();
    }
}
