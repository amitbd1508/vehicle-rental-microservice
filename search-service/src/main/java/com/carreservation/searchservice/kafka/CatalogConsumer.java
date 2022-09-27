package com.carreservation.searchservice.kafka;

import com.carreservation.searchservice.entity.Vehicle;
import com.carreservation.searchservice.entity.VehicleSearchItem;
import com.carreservation.searchservice.repository.VehicleSearchItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatalogConsumer {

    @Autowired
    private VehicleSearchItemRepository vehicleSearchItemRepository;

    @org.springframework.kafka.annotation.KafkaListener(groupId = KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME)
    public void listen(byte[] byteData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Vehicle data = mapper.readValue(byteData, Vehicle.class);

        System.out.println("===================== Ommm nommm nommm, I am so fat, I cannot eat more, but I still ate your data ==========================");
        System.out.println(data);

        VehicleSearchItem vehicleSearchItem = new VehicleSearchItem();
        vehicleSearchItem.setCatalogName(data.getCatalogName());
        vehicleSearchItem.setBrand(data.getBrand());
        vehicleSearchItem.setModel(data.getModel());
        vehicleSearchItem.setColor(data.getColor());
        vehicleSearchItem.setYearOfManufacture(data.getYearOfManufacture());
        vehicleSearchItem.setPlateNumber(data.getPlateNumber());
        vehicleSearchItem.setPrice(data.getPrice());
        vehicleSearchItemRepository.save(vehicleSearchItem);
    }
}
