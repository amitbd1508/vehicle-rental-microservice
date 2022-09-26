package com.carreservation.catalogservice.service;

import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Page<Vehicle> getAllVehicle(Optional<Integer> page, Optional<Integer> size);
    Page<Vehicle> getVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size);
    Page<Vehicle> getVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size);
    Vehicle getVehicleById(String vehicleId);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String vehicleId,  Vehicle vehicle);
    Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus);
    void deleteVehicle(String vehicleId, Vehicle vehicle);
    Vehicle save(Vehicle vehicle);
}
