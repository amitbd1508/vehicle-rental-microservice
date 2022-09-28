package com.carreservation.searchservice.service.impl;

import com.carreservation.searchservice.entity.VehicleSearchItem;
import com.carreservation.searchservice.repository.VehicleSearchItemRepository;
import com.carreservation.searchservice.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.carreservation.searchservice.util.Constants.PAGE_SIZE;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleSearchItemRepository vehicleSearchItemRepository;

    @Override
    public Page<VehicleSearchItem> searchVehicleByBrand(String brand, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(PAGE_SIZE));
        log.info("Vehicle with brand {} is retrieved from database", brand);
        return vehicleSearchItemRepository.searchByBrand(brand, pageable);
    }

    @Override
    public Page<VehicleSearchItem> searchVehicleByModel(String model, Optional<Integer> page, Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(PAGE_SIZE));
        log.info("Vehicle with model {} is retrieved from database", model);
        return vehicleSearchItemRepository.searchByModel(model, pageable);
    }

}
