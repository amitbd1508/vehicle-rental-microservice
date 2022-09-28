package com.carreservation.searchservice.repository;

import com.carreservation.searchservice.entity.VehicleSearchItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VehicleSearchItemRepository extends ElasticsearchRepository<VehicleSearchItem, Long> {

    Page<VehicleSearchItem> searchByBrand(String brand, Pageable pageable);

    Page<VehicleSearchItem> searchByModel(String model, Pageable pageable);

}
