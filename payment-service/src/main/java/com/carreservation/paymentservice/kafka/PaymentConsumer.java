package com.carreservation.paymentservice.kafka;

import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import com.carreservation.paymentservice.repository.PaymentRepository;
import com.carreservation.paymentservice.service.PaymentService;
import com.carreservation.paymentservice.service.PaymentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentConsumer {
    @Autowired
    PaymentService paymentService;

    //@Autowired
    //private PaymentRepository paymentRepository;
    @org.springframework.kafka.annotation.KafkaListener(groupId =KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME_RESERVATION_CREATED )
    public void listen(byte[] data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentRequestDTO pojo = mapper.readValue(data, PaymentRequestDTO.class);


        paymentService.processPayment(pojo);
        System.out.println("=====================Reservation Created Listener ==========================");
        System.out.println(data);
    }
}
