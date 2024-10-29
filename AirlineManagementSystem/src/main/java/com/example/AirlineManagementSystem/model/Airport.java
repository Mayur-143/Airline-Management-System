package com.example.AirlineManagementSystem.model;

public class Airport {

    private int airportId;
    private String airportName;
    private String IATA_code;
    private String location;
    private String address;

    // Constructors
    public Airport() {}

    public Airport(int airportId, String airportName, String IATA_code, String location, String address) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.IATA_code = IATA_code;
        this.location = location;
        this.address = address;
    }

    // Getters and Setters
    public int getAirportId() { return airportId; }
    public void setAirportId(int airportId) { this.airportId = airportId; }

    public String getAirportName() { return airportName; }
    public void setAirportName(String airportName) { this.airportName = airportName; }

    public String getIATA_code() { return IATA_code; }
    public void setIATA_code(String IATA_code) { this.IATA_code = IATA_code; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // toString
    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", airportName='" + airportName + '\'' +
                ", IATA_code='" + IATA_code + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

