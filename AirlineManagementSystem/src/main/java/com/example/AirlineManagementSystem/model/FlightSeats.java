package com.example.AirlineManagementSystem.model;

public class FlightSeats {
    private int flightSeatId;               // Primary key for the flight seats record
    private int flightId;                   // Foreign key to the flight
    private int availableEconomySeats;
    private int availableBusinessSeats;
    private int availableFirstClassSeats;

    // Getters and Setters
    public int getFlightSeatId() {
        return flightSeatId;
    }

    public void setFlightSeatId(int flightSeatId) {
        this.flightSeatId = flightSeatId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getAvailableEconomySeats() {
        return availableEconomySeats;
    }

    public void setAvailableEconomySeats(int availableEconomySeats) {
        this.availableEconomySeats = availableEconomySeats;
    }

    public int getAvailableBusinessSeats() {
        return availableBusinessSeats;
    }

    public void setAvailableBusinessSeats(int availableBusinessSeats) {
        this.availableBusinessSeats = availableBusinessSeats;
    }

    public int getAvailableFirstClassSeats() {
        return availableFirstClassSeats;
    }

    public void setAvailableFirstClassSeats(int availableFirstClassSeats) {
        this.availableFirstClassSeats = availableFirstClassSeats;
    }
}
