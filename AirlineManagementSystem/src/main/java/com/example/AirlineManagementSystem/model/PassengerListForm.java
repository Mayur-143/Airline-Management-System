package com.example.AirlineManagementSystem.model;

import java.util.List;
import java.util.ArrayList;

public class PassengerListForm {
    private List<Passenger> passengers = new ArrayList<>(); // Initialize the list

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
