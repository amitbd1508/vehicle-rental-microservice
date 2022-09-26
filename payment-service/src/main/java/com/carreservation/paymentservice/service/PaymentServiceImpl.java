package com.carreservation.paymentservice.service;

import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.domain.PaymentStatus;
import com.carreservation.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public PaymentRequest makePayment(PaymentRequest request){
        request.setPaymentStatus(PaymentStatus.PAID);
        request.setPaymentType(request.getPaymentType());
        request.setUserId(request.getUserId());
        request.setPrice(request.getPrice());
        request.setReservationId(request.getReservationId());
        return paymentRepository.save(request);
    }
}