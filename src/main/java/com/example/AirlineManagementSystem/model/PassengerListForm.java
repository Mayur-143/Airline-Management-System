package com.example.AirlineManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class PassengerListForm {
    private List<Passenger> passengers = new ArrayList<>(); // Initialize the list

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
