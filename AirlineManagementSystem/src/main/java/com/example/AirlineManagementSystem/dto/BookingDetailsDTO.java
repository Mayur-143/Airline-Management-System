package com.example.AirlineManagementSystem.dto;

public class BookingDetailsDTO {
    private int bookingId;
    private String bookingDateTime;
    private String status;
    private String passengerName;
    private int age;
    private String gender;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String arrivalAirport;
    private String departureAirport;
    private int seatFare;
    private String flightClass;
    
    
    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    public String getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    public int getSeatFare() {
        return seatFare;
    }
    public void setSeatFare(int seatFare) {
        this.seatFare = seatFare;
    }
    public String getFlightClass() {
        return flightClass;
    }
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    
}
