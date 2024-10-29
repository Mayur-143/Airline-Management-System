package com.example.AirlineManagementSystem.model;

import java.time.LocalDateTime;

public class Flight {

    private int flightId;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String flightStatus;
    private int economySeatFare;
    private int businessSeatFare;
    private int firstClassSeatFare;
    private int airportId;
    private int airplaneId;

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

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        this.airplaneId = airplaneId;
    }

    // Constructors
    public Flight() {}

    public Flight(int flightId, String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime,
                  String flightStatus, int economySeatFare, int businessSeatFare, int firstClassSeatFare,
                  int airportId, int airplaneId) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightStatus = flightStatus;
        this.economySeatFare = economySeatFare;
        this.businessSeatFare = businessSeatFare;
        this.firstClassSeatFare = firstClassSeatFare;
        this.airportId = airportId;
        this.airplaneId = airplaneId;
    }

    

    // toString
    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", flightStatus='" + flightStatus + '\'' +
                ", economySeatFare=" + economySeatFare +
                ", businessSeatFare=" + businessSeatFare +
                ", firstClassSeatFare=" + firstClassSeatFare +
                ", airportId=" + airportId +
                ", airplaneId=" + airplaneId +
                '}';
    }
}
