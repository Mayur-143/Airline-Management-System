package com.example.AirlineManagementSystem.dto;

import java.time.LocalDateTime;

public class FlightDTO {
    private int flightId;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String flightStatus;
    private int economySeatFare;
    private int businessSeatFare;
    private int firstClassSeatFare;

    private String departureAirportName;
    private String arrivalAirportName;
    private String airplaneName;
    private int airplaneId;

    // Getters and setters for all fields

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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public int getEconomySeatFare() {
        return economySeatFare;
    }

    public void setEconomySeatFare(int economySeatFare) {
        this.economySeatFare = economySeatFare;
    }

    public int getBusinessSeatFare() {
        return businessSeatFare;
    }

    public void setBusinessSeatFare(int businessSeatFare) {
        this.businessSeatFare = businessSeatFare;
    }

    public int getFirstClassSeatFare() {
        return firstClassSeatFare;
    }

    public void setFirstClassSeatFare(int firstClassSeatFare) {
        this.firstClassSeatFare = firstClassSeatFare;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public int getAirplaneId() {  // Add getter for airplaneId
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {  // Add setter for airplaneId
        this.airplaneId = airplaneId;
    }
}
