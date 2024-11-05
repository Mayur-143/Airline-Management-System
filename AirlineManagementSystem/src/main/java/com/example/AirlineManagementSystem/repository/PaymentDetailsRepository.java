package com.example.AirlineManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.AirlineManagementSystem.model.PaymentDetails;
import com.example.AirlineManagementSystem.rowmapper.PaymentDetailsRowMapper;

@Repository
public class PaymentDetailsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_QUERY = "INSERT INTO payment_details (card_number, card_holder_name, cvv, expiry_date, booking_id, amount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE payment_details SET card_number=?, card_holder_name=?, cvv=?, expiry_date=?, booking_id=?, amount=? WHERE payment_id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM payment_details WHERE payment_id=?";
    private static final String DELETE_QUERY = "DELETE FROM payment_details WHERE payment_id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM payment_details";

    public void save(PaymentDetails paymentDetails) {
        jdbcTemplate.update(INSERT_QUERY, paymentDetails.getCardNumber(), paymentDetails.getCardHolderName(), 
                            paymentDetails.getCvv(), paymentDetails.getExpiryDate(), paymentDetails.getBookingId(), 
                            paymentDetails.getAmount());
    }

    public void update(PaymentDetails paymentDetails) {
        jdbcTemplate.update(UPDATE_QUERY, paymentDetails.getCardNumber(), paymentDetails.getCardHolderName(), 
                            paymentDetails.getCvv(), paymentDetails.getExpiryDate(), paymentDetails.getBookingId(), 
                            paymentDetails.getAmount(), paymentDetails.getPaymentId());
    }

    public Optional<PaymentDetails> findById(int paymentId) {
        return jdbcTemplate.query(SELECT_BY_ID_QUERY, new PaymentDetailsRowMapper(), paymentId)
                           .stream().findFirst();
    }

    public List<PaymentDetails> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new PaymentDetailsRowMapper());
    }

    public void deleteById(int paymentId) {
        jdbcTemplate.update(DELETE_QUERY, paymentId);
    }
}

