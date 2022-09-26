package com.carreservation.paymentservice.controller;

import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import com.carreservation.paymentservice.dto.adapters.PaymentReqToDTOAdapter;
import com.carreservation.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate;

    public PaymentController(KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public PaymentRequest processPayment(@RequestBody PaymentRequest request) {
        PaymentRequest paymentRequest=paymentService.makePayment(request);
        PaymentRequestDTO paymentRequestDTO= PaymentReqToDTOAdapter.createPaymentRequestDTO(paymentRequest);
        kafkaTemplate.send("paymentnotifier", paymentRequestDTO);
        return paymentRequest;
    }
}
