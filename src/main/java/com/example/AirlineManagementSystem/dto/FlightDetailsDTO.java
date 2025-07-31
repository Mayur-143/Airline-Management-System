package com.example.AirlineManagementSystem.dto;

import java.time.LocalDateTime;

public class FlightDetailsDTO {
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

    // New airplane-specific fields
    private String airplaneModel;
    private int totalSeats;
    private int totalEconomySeats;
    private int totalBusinessSeats;
    private int totalFirstClassSeats;
    private int availableEconomySeats;
    private int availableBusinessSeats;
    private int availableFirstClassSeats;

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

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalEconomySeats() {
        return totalEconomySeats;
    }

    public void setTotalEconomySeats(int totalEconomySeats) {
        this.totalEconomySeats = totalEconomySeats;
    }

    public int getTotalBusinessSeats() {
        return totalBusinessSeats;
    }

    public void setTotalBusinessSeats(int totalBusinessSeats) {
        this.totalBusinessSeats = totalBusinessSeats;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public void setTotalFirstClassSeats(int totalFirstClassSeats) {
        this.totalFirstClassSeats = totalFirstClassSeats;
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
