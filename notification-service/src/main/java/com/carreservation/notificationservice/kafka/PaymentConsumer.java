package com.carreservation.notificationservice.kafka;

import com.carreservation.notificationservice.model.PaymentNotification;
import com.carreservation.notificationservice.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentConsumer {


    @Autowired
    private NotificationService notiService;
    @org.springframework.kafka.annotation.KafkaListener(groupId =KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.PAYMENT_NOTIFICATION )
    public void listen(byte[] data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentNotification pojo = mapper.readValue(data, PaymentNotification.class);

        notiService.sendPaymentNotifications(pojo);
        System.out.println("=====================Notification listener Listener ==========================");
        System.out.println(data);
    }
}
