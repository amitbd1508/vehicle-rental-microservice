package com.carreservation.catalogservice.service;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import com.carreservation.catalogservice.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private CatalogRepo catalogRepo;

    @Override
    public List<Vehicle> getAllVehicle() {
        return catalogRepo.findAll();
    }
    @Override
    public List<Vehicle> getVehicleByBrand(String brand) {
        Criteria caseInsensitive = Criteria.where("brand").regex(brand, "i");
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
    public Vehicle addVehicle(Vehicle vehicle) {
        return catalogRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(String vehicleId, Vehicle vehicle) {
        if(catalogRepo.findById(vehicleId).isPresent())
        {
            vehicle.setId(vehicleId);
        }

        return catalogRepo.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleStatus(String vehicleId, VehicleStatus vehicleStatus) {

        if (catalogRepo.findById(vehicleId).isPresent())
        {
            catalogRepo.findById(vehicleId).get().setVehicleStatus(vehicleStatus);
        }
        return catalogRepo.save(catalogRepo.findById(vehicleId).get());
    }

    @Override
    public void deleteVehicle(String vehicleId, Vehicle vehicle) {
        catalogRepo.deleteById(vehicleId);
    }
}
