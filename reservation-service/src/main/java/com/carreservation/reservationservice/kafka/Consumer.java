package com.carreservation.reservationservice.kafka;

import com.carreservation.reservationservice.entity.UpdateReservationMessage;
import com.carreservation.reservationservice.kafkamodels.PaymentRequestDTO;
import com.carreservation.reservationservice.service.ReservationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {
    @Autowired
    ReservationServiceImpl reservationService;
    @org.springframework.kafka.annotation.KafkaListener(groupId = KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.UPDATE_RES_STATUS )
    public void listen(byte[] data) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        UpdateReservationMessage pojo = mapper.readValue(data, UpdateReservationMessage.class);

        reservationService.UpdateReservationStatus(pojo);

        System.out.println("=====================From Update Reservation status==========================");
        System.out.println(data);
    }
}
