package com.carreservation.paymentservice.service;


import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
        PaymentRequest makePayment(PaymentRequest request);
        void processPayment(PaymentRequestDTO paymentRequestDTO);
    }