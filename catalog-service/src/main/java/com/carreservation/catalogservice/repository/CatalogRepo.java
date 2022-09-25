package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CatalogRepo extends MongoRepository<Vehicle, String> {
    @Query(value = "{'brand': {$regex : ?0, $options: 'i'}}")
    List<Vehicle> getByBrand(
            String brand
    );

    List<Vehicle> getByModel(String model);

    Vehicle getVehicleById(String vehicleId);

    }
