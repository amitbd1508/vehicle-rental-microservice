package com.carreservation.catalogservice.service.impl;


import com.carreservation.catalogservice.kafka.KafkaConfig;
import com.carreservation.catalogservice.model.entity.Vehicle;
import com.carreservation.catalogservice.model.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import com.carreservation.catalogservice.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.carreservation.catalogservice.util.Constants.SIZE;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class VehicleServiceImp implements VehicleService {
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KafkaTemplate<String, Vehicle> kafkaTemplate;

    @Override
    public Page<Vehicle> getAllVehicle(Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        return catalogRepo.findAll(pageable);
    }
    @Override
    public Page<Vehicle> getVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        return catalogRepo.getByBrand(brand, pageable);
    }
    @Override
    public Page<Vehicle> getVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        return catalogRepo.getByModel(model, pageable);
    }
    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return catalogRepo.findById(vehicleId).orElse(null);
    }

    @Override
    public Vehicle updateVehicle(String vehicleId, Vehicle vehicle) {
        if(catalogRepo.findById(vehicleId).isPresent())
        {
            vehicle.setId(vehicleId);
        }

        return catalogRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus) {

        if (catalogRepo.findById(vehicleId).isPresent())
        {
            catalogRepo.findById(vehicleId).get().setVehicleStatus(vehicleStatus);
        }
        return catalogRepo.save(catalogRepo.findById(vehicleId).get());
    }

    @Override
    public void deleteVehicle(String vehicleId, Vehicle vehicle) {
        catalogRepo.deleteById(vehicleId);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        Vehicle v = modelMapper.map(vehicle, Vehicle.class);
        var res = catalogRepo.save(v);
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, res);
        return res;
    }

}
