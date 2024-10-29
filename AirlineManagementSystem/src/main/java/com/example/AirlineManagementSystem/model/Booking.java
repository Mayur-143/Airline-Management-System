package com.example.AirlineManagementSystem.model;


import java.time.LocalDateTime;

public class Booking {
    
    private int bookingId;
    private LocalDateTime bookingDate;
    private int totalPassenger;
    private String status;
    private int userId;
    private int flightId;

    // Constructor
    public Booking() {}

    public Booking(int bookingId, LocalDateTime bookingDate, int totalPassenger, String status, int userId, int flightId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.totalPassenger = totalPassenger;
        this.status = status;
        this.userId = userId;
        this.flightId = flightId;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTotalPassenger() {
        return totalPassenger;
    }

    public void setTotalPassenger(int totalPassenger) {
        this.totalPassenger = totalPassenger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate=" + bookingDate +
                ", totalPassenger=" + totalPassenger +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", flightId=" + flightId +
                '}';
    }
}

