package com.example.AirlineManagementSystem.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.example.AirlineManagementSystem.model.PaymentDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentDetailsRowMapper implements RowMapper<PaymentDetails> {
    @Override
    public PaymentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentId(rs.getInt("payment_id"));
        paymentDetails.setCardNumber(rs.getInt("card_number"));
        paymentDetails.setCardHolderName(rs.getString("card_holder_name"));
        paymentDetails.setCvv(rs.getInt("cvv"));
        paymentDetails.setExpiryDate(rs.getDate("expiry_date"));
        paymentDetails.setBookingId(rs.getInt("booking_id"));
        paymentDetails.setAmount(rs.getInt("amount"));
        return paymentDetails;
    }
}

