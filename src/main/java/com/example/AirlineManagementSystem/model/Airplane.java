package com.example.AirlineManagementSystem.model;

import java.util.Objects;

public class Airplane {

    private int airplaneId;
    private String registrationNo;
    private String description;
    private String model;
    private int totalSeats;
    private int totalEconomySeats;
    private int totalBusinessSeats;
    private int totalFirstClassSeats;

    // Constructors
    public Airplane() {}

    public Airplane(int airplaneId, String registrationNo, String description, String model, int totalSeats,
                    int totalEconomySeats, int totalBusinessSeats, int totalFirstClassSeats) {
        this.airplaneId = airplaneId;
        this.registrationNo = registrationNo;
        this.description = description;
        this.model = model;
        this.totalSeats = totalSeats;
        this.totalEconomySeats = totalEconomySeats;
        this.totalBusinessSeats = totalBusinessSeats;
        this.totalFirstClassSeats = totalFirstClassSeats;
    }

    // Getters and Setters
    public int getAirplaneId() { return airplaneId; }
    public void setAirplaneId(int airplaneId) { this.airplaneId = airplaneId; }

    public String getRegistrationNo() { return registrationNo; }
    public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getTotalEconomySeats() { return totalEconomySeats; }
    public void setTotalEconomySeats(int totalEconomySeats) { this.totalEconomySeats = totalEconomySeats; }

    public int getTotalBusinessSeats() { return totalBusinessSeats; }
    public void setTotalBusinessSeats(int totalBusinessSeats) { this.totalBusinessSeats = totalBusinessSeats; }

    public int getTotalFirstClassSeats() { return totalFirstClassSeats; }
    public void setTotalFirstClassSeats(int totalFirstClassSeats) { this.totalFirstClassSeats = totalFirstClassSeats; }

    // toString
    @Override
    public String toString() {
        return "Airplane{" +
                "airplaneId=" + airplaneId +
                ", registrationNo='" + registrationNo + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", totalSeats=" + totalSeats +
                ", totalEconomySeats=" + totalEconomySeats +
                ", totalBusinessSeats=" + totalBusinessSeats +
                ", totalFirstClassSeats=" + totalFirstClassSeats +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return airplaneId == airplane.airplaneId &&
                totalSeats == airplane.totalSeats &&
                totalEconomySeats == airplane.totalEconomySeats &&
                totalBusinessSeats == airplane.totalBusinessSeats &&
                totalFirstClassSeats == airplane.totalFirstClassSeats &&
                Objects.equals(registrationNo, airplane.registrationNo) &&
                Objects.equals(description, airplane.description) &&
                Objects.equals(model, airplane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airplaneId, registrationNo, description, model, totalSeats, totalEconomySeats,
                            totalBusinessSeats, totalFirstClassSeats);
    }
}
