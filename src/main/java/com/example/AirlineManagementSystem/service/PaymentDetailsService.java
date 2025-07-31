package com.example.AirlineManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AirlineManagementSystem.model.PaymentDetails;
import com.example.AirlineManagementSystem.repository.PaymentDetailsRepository;

@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    public void savePaymentDetails(PaymentDetails paymentDetails) {
        paymentDetailsRepository.save(paymentDetails);
    }

    public void updatePaymentDetails(PaymentDetails paymentDetails) {
        paymentDetailsRepository.update(paymentDetails);
    }

    public Optional<PaymentDetails> getPaymentDetailsById(int paymentId) {
        return paymentDetailsRepository.findById(paymentId);
    }

    public List<PaymentDetails> getAllPaymentDetails() {
        return paymentDetailsRepository.findAll();
    }

    public void deletePaymentDetailsById(int paymentId) {
        paymentDetailsRepository.deleteById(paymentId);
    }
}

