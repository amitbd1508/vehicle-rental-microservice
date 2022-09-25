package com.carreservation.calalogservice.service;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import lombok.Data;

import java.util.List;
import java.util.Optional;

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
    public Vehicle getVehicleById(Integer vehicleId) {
        return catalogRepo.findById(vehicleId).get();
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return catalogRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Integer vehicleId, Vehicle vehicle) {
        if(catalogRepo.findById(vehicleId).isPresent())
        {
            vehicle.setId(vehicleId);
        }

        return catalogRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleStatus(Integer vehicleId, VehicleStatus vehicleStatus) {

        if (catalogRepo.findById(vehicleId).isPresent())
        {
            catalogRepo.findById(vehicleId).get().setVehicleStatus(vehicleStatus);
        }
        return catalogRepo.save(catalogRepo.findById(vehicleId).get());
    }

    @Override
    public void deleteVehicle(Integer vehicleId, Vehicle vehicle) {
        catalogRepo.deleteById(vehicleId);
    }
}