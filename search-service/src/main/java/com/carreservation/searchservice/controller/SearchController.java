package com.carreservation.searchservice.controller;


import com.carreservation.searchservice.entity.VehicleSearchItem;
import com.carreservation.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/bybrand/{brand}")
    public Page<VehicleSearchItem> searchVehicleByBrand(@PathVariable String brand, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return searchService.searchVehicleByBrand(brand, page, size);
    }

    @GetMapping("/bymodel/{model}")
    public Page<VehicleSearchItem> searchVehicleByModel(@PathVariable String model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return searchService.searchVehicleByModel(model, page, size);
    }
}



