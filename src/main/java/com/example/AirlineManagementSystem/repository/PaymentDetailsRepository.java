package com.example.AirlineManagementSystem.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public PaymentDetails save(PaymentDetails paymentDetails) {
        String sql = "INSERT INTO payment_details (card_number, card_holder_name, cvv, expiry_date, booking_id, amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
    
        // Use KeyHolder to retrieve the auto-generated paymentId
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "payment_id" });
            ps.setString(1, paymentDetails.getCardNumber());
            ps.setString(2, paymentDetails.getCardHolderName());
            ps.setInt(3, paymentDetails.getCvv());
            ps.setDate(4, new java.sql.Date(paymentDetails.getExpiryDate().getTime()));
            ps.setInt(5, paymentDetails.getBookingId());
            ps.setInt(6, paymentDetails.getAmount());
            return ps;
        }, keyHolder);
    
        // Set the generated paymentId back to the PaymentDetails object
        paymentDetails.setPaymentId(keyHolder.getKey().intValue());
        return paymentDetails;
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

