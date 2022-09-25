package com.carreservation.calalogservice.service;

import com.carreservation.catalogservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicle();

    List<Vehicle> getVehicleByBrand();

    List<Vehicle> getVehicleByModel();



}
