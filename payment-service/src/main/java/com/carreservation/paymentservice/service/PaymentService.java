package com.carreservation.paymentservice.service;


import com.carreservation.paymentservice.domain.PaymentRequest;

public interface PaymentService {
        PaymentRequest makePayment(PaymentRequest request);
    }