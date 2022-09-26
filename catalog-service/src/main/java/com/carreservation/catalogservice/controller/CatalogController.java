package com.carreservation.catalogservice.controller;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carcatalog")
public class CatalogController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public Page<Vehicle> getAllVehicle(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return vehicleService.getAllVehicle(page, size);
    }

    @GetMapping("/bybrand/{brand}")
    public Page<Vehicle> getVehicleByBrand(@PathVariable String brand, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return vehicleService.getVehicleByBrand(brand, page, size);
    }


    @GetMapping("/bymodel/{model}")
    public Page<Vehicle> getVehicleByModel(@PathVariable String model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return vehicleService.getVehicleByModel(model, page, size);
    }
}



