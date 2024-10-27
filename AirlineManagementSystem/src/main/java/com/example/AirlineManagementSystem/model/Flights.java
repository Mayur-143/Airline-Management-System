package com.example.AirlineManagementSystem.model;

import java.util.Date;

public class Flights {
    private int flightId;           // Unique identifier for each flight
    private String flightNumber;     // Unique flight number
    private String departureLocation; // Origin city or airport
    private String arrivalLocation;   // Destination city or airport
    private Date departureTime;       // Scheduled departure time
    private Date arrivalTime;         // Scheduled arrival time
    private int duration;             // Flight duration in minutes
    private int aircraftId;          // Foreign key referencing Aircraft table
    private String status;            // Status of the flight (Scheduled, Delayed, Cancelled, etc.)
    private int noOfPassengers;       // Number of passengers
    private int airportId;           // Foreign key referencing Airport table

    // Constructor
    public Flights() {}

    // Getters and Setters
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", duration=" + duration +
                ", aircraftId=" + aircraftId +
                ", status='" + status + '\'' +
                ", noOfPassengers=" + noOfPassengers +
                ", airportId=" + airportId +
                '}';
    }
}

