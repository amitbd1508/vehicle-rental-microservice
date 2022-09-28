package com.carreservation.catalogservice.service;

import com.carreservation.catalogservice.model.dto.VehicleRequestDto;
import com.carreservation.catalogservice.model.entity.Vehicle;
import com.carreservation.catalogservice.model.entity.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface VehicleService {
    Page<Vehicle> getAllVehicle(Optional<Integer> page, Optional<Integer> size);
    Page<Vehicle> getVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size);
    Page<Vehicle> getVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size);
    Vehicle getVehicleById(String vehicleId);
    Vehicle updateVehicle(String vehicleId,  Vehicle vehicle);
    void deleteVehicle(String vehicleId);
    Vehicle save(Vehicle vehicle);
}
