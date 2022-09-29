package com.carreservation.catalogservice.service.impl;


import com.carreservation.catalogservice.kafka.KafkaConfig;
import com.carreservation.catalogservice.model.entity.Vehicle;
import com.carreservation.catalogservice.model.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import com.carreservation.catalogservice.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@Slf4j
public class VehicleServiceImp implements VehicleService {
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KafkaTemplate<String, Vehicle> kafkaTemplate;

    //    @Cacheable(cacheNames="vehicles")
    @Override
    public Page<Vehicle> getAllVehicle(Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        log.info("All Vehicles are retrieved from database");
        return catalogRepo.findAll(pageable);
    }


    @Override
    public Page<Vehicle> getVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        log.info("Vehicle with brand {} is retrieved from database", brand);
        return catalogRepo.getByBrand(brand, pageable);
    }
    @Override
    public Page<Vehicle> getVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(SIZE));
        log.info("Vehicle with model {} is retrieved from database", model);
        return catalogRepo.getByModel(model, pageable);
    }

//    @Cacheable(cacheNames="vehicles", key="#vehicleId")
    @Override
    public Vehicle getVehicleById(String vehicleId) {
        log.info("Vehicle with id {} is retrieved from database", vehicleId);
        return catalogRepo.findById(vehicleId).orElse(null);
    }

    @Override
//    @CachePut(cacheNames="vehicles", key="#vehicleId")
    public Vehicle updateVehicle(String vehicleId, Vehicle vehicle) {
        if(catalogRepo.findById(vehicleId).isPresent())
        {
            vehicle.setId(vehicleId);
        }
        log.info("Vehicle with id {} is updated in database", vehicleId);
        return catalogRepo.save(vehicle);
    }

    @Override
//    @CacheEvict(cacheNames="vehicles", key="#vehicleId")
    public void deleteVehicle(String vehicleId) {
        catalogRepo.deleteById(vehicleId);
        log.info("Vehicle with id: " + vehicleId + " deleted");
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        Vehicle v = modelMapper.map(vehicle, Vehicle.class);
        var res = catalogRepo.save(v);
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, res);
        log.info("Vehicle with id {} is saved in database", vehicle.getId());
        return res;
    }

}
