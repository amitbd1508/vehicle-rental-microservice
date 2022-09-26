package com.carreservation.catalogservice.service;

import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicle();
    List<Vehicle> getVehicleByBrand(String brand);
    List<Vehicle> getVehicleByModel(String model);
    Vehicle getVehicleById(String vehicleId);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String vehicleId,  Vehicle vehicle);
    Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus);
    void deleteVehicle(String vehicleId, Vehicle vehicle);

}
