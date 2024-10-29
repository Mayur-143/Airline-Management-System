package com.example.AirlineManagementSystem.model;

public class Passenger {
    private int passengerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private int age;
    private int aadharNumber;
    private int bookingId;

    // Constructors
    public Passenger() {}

    public Passenger(int passengerId, String firstName, String middleName, String lastName, String gender, int age, int aadharNumber, int bookingId) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.aadharNumber = aadharNumber;
        this.bookingId = bookingId;
    }

    // Getters and Setters
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(int aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", aadharNumber=" + aadharNumber +
                ", bookingId=" + bookingId +
                '}';
    }
}

