package com.example.AirlineManagementSystem.model;

public class Contact {
    private int contactId;
    private String email;
    private String name;
    private String phoneNumber;
    private String message;
    private int userId;

    // Constructors
    public Contact() {}

    public Contact(int contactId, String email, String name, String phoneNumber, String message, int userId) {
        this.contactId = contactId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.userId = userId;
    }

    // Getters and Setters
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }
}

