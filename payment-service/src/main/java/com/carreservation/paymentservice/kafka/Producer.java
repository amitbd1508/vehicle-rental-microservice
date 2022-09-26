package com.carreservation.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String TOPIC="test_topic"; //search
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message){

        this.kafkaTemplate.send(TOPIC, message);
    }
}