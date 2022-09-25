package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogRepo extends MongoRepository<Vehicle, String> {
    List<Vehicle> getByBrand(
            String brand
    );

    List<Vehicle> getByModel(String model);

    Vehicle getVehicleById(String vehicleId);

    }
