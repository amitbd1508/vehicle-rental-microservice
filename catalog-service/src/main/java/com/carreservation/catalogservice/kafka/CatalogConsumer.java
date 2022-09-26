package com.carreservation.catalogservice.kafka;

import com.carreservation.catalogservice.model.dto.VehicleRequestDto;
import com.carreservation.catalogservice.model.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class CatalogConsumer {
    @org.springframework.kafka.annotation.KafkaListener(groupId = KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME )
    public void listen(Vehicle data) {
        System.out.println("=====================Hello ==========================");
        System.out.println(data);
    }
}
