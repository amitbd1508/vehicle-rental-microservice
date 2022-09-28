package com.carreservation.searchservice.service;

import com.carreservation.searchservice.entity.VehicleSearchItem;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SearchService {

    Page<VehicleSearchItem> searchVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size);

    Page<VehicleSearchItem> searchVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size);

}
