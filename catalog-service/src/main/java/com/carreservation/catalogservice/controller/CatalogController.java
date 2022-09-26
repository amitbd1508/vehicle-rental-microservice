package com.carreservation.catalogservice.controller;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carcatalog")
public class CatalogController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/bybrand/{brand}")
    public List<Vehicle> getVehicleByBrand(@PathVariable String brand) {
        return vehicleService.getVehicleByBrand(brand);
    }


    @GetMapping("/bymodel/{model}")
    public List<Vehicle> getVehicleByModel(@PathVariable String model) {
        return vehicleService.getVehicleByModel(model);
    }

    @GetMapping("/catalog/{pageNo}/{pageSize}")
    public List<Vehicle> getPaginatedCountries(@PathVariable int pageNo,
                                               @PathVariable int pageSize) {

        return  VehicleService.findPaginated(pageNo, pageSize);
    }
}



