package com.example.AirlineManagementSystem.model;

public class Employee {
    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String designation;
    private String mobileNumber; // Changed to String for better handling of phone numbers
    private int airportId;

    // Constructors
    public Employee() {}

    public Employee(int employeeId, String firstName, String middleName, String lastName, String street,
                    String city, String state, String country, String pinCode, String designation,
                    String mobileNumber, int airportId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.airportId = airportId;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", designation='" + designation + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", airportId=" + airportId +
                '}';
    }
}

