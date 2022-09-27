package com.carreservation.paymentservice.controller;

import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import com.carreservation.paymentservice.dto.adapters.PaymentReqToDTOAdapter;
import com.carreservation.paymentservice.kafka.KafkaConfig;
import com.carreservation.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate;



//    @PostMapping
//    public PaymentRequest processPayment(@RequestBody PaymentRequest request) {
//        PaymentRequest paymentRequest=paymentService.makePayment(request);
//        PaymentRequestDTO paymentRequestDTO= PaymentReqToDTOAdapter.createPaymentRequestDTO(paymentRequest);
//        //kafkaTemplate.send("paymentnotifier", paymentRequestDTO);
//        return paymentRequest;
//    }

    @GetMapping("/makepayment")
    public String pay(){

        try {
            PaymentRequestDTO ct= new PaymentRequestDTO();
            ct.setPaymentType("Kafka testing");
            ct.setUserId("Kafka testing");
            ct.setQueueId("Kafka testing");
            ct.setAmount(15.6);
            //kafkaTemplate.send(KafkaConfig.TOPIC_NAME, ct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json message sent succuessfully";
    }
}
