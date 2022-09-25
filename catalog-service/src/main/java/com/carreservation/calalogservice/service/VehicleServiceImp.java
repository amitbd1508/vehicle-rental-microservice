package com.carreservation.calalogservice.service;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;
import lombok.Data;

import java.util.List;

@Data

public class VehicleServiceImp implements VehicleService {
    @Override
    public List<Vehicle> getAllVehicle() {
        return null;
    }

    @Override
    public List<Vehicle> getVehicleByBrand() {
        return null;
    }

    @Override
    public List<Vehicle> getVehicleByModel() {
        return null;
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return null;
    }

    @Override
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return null;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle updateVehicle(String vehicleId, Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus) {
        return null;
    }

    @Override
    public void deleteVehicle(String vehicleId, Vehicle vehicle) {

    }
}
