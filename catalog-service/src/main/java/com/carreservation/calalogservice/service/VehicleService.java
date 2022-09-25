package com.carreservation.calalogservice.service;

import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicle();

    List<Vehicle> getVehicleByBrand();

    List<Vehicle> getVehicleByModel();

    Vehicle getVehicleById(String vehicleId);

    Vehicle getVehicleByPlateNumber(String plateNumber);

    Vehicle addVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String vehicleId,  Vehicle vehicle);
    Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus);
    void deleteVehicle(String vehicleId, Vehicle vehicle);






}
