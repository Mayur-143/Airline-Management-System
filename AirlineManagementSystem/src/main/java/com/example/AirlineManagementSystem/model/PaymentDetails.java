package com.example.AirlineManagementSystem.model;

import java.util.Date;

public class PaymentDetails {
    private int paymentId;
    private int cardNumber;
    private String cardHolderName;
    private int cvv;
    private Date expiryDate;
    private int bookingId;
    private int amount;

    // Constructors
    public PaymentDetails() {}

    public PaymentDetails(int paymentId, int cardNumber, String cardHolderName, int cvv, Date expiryDate, int bookingId, int amount) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.bookingId = bookingId;
        this.amount = amount;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentId=" + paymentId +
                ", cardNumber=" + cardNumber +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cvv=" + cvv +
                ", expiryDate=" + expiryDate +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                '}';
    }
}

