package com.carreservation.catalogservice.service;

import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicle();
    List<Vehicle> getVehicleByBrand(String brand);
    List<Vehicle> getVehicleByModel(String model);
    Vehicle getVehicleById(Integer vehicleId);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Integer vehicleId,  Vehicle vehicle);
    Vehicle updateVehicleStatus(Integer vehicleId, VehicleStatus vehicleStatus);
    void deleteVehicle(Integer vehicleId, Vehicle vehicle);
}
