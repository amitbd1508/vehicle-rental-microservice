package com.carreservation.catalogservice.kafka;

import com.carreservation.catalogservice.model.dto.CatalogMessageDTO;
import com.carreservation.catalogservice.model.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatalogConsumer {

    @Autowired
    private CatalogRepo catalogRepo;

    @org.springframework.kafka.annotation.KafkaListener(groupId = KafkaConfig.GROUP_ID_FOR_RESERVATION, topics = KafkaConfig.TOPIC_NAME_RESERVATION)
    public void listen(byte[] byteData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CatalogMessageDTO data = mapper.readValue(byteData, CatalogMessageDTO.class);

        System.out.println("===================== Ommm nommm nommm, I am so fat, I cannot eat more, but I still ate your data ==========================");
        System.out.println(data);

        catalogRepo.findById(data.getVehicleId()).ifPresent(vehicle -> {
            vehicle.setVehicleStatus(data.getReservationStatus().equalsIgnoreCase("RESERVED") ? VehicleStatus.RESERVED : VehicleStatus.AVAILABLE);
            catalogRepo.save(vehicle);
        });

    }
}
