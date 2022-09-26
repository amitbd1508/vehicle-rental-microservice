package com.carreservation.paymentservice.kafka;

import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    @org.springframework.kafka.annotation.KafkaListener(groupId =KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME )
    public void listen(PaymentRequestDTO data) {
        System.out.println("=====================Hello ==========================");
        System.out.println(data);
    }
}
