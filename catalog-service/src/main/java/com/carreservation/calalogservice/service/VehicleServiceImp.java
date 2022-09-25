package com.carreservation.calalogservice.service;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import lombok.Data;

import java.util.List;

@Data

public class VehicleServiceImp implements VehicleService {

    private CatalogRepo catalogRepo;
    @Override
    public List<Vehicle> getAllVehicle() {
        return catalogRepo.findAll();
    }

    @Override
    public List<Vehicle> getVehicleByBrand(String brand) {
        return catalogRepo.getByBrand(brand);
    }

    @Override
    public List<Vehicle> getVehicleByModel(String model) {
        return catalogRepo.getByModel(model);
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {
        return catalogRepo.findById(vehicleId).get();
    }

    @Override
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return catalogRepo.findby(plateNumber).get();
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
