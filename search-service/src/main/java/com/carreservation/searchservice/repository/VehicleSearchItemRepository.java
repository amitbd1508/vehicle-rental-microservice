package com.carreservation.searchservice.repository;

import com.carreservation.searchservice.entity.VehicleSearchItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VehicleSearchItemRepository extends ElasticsearchRepository<VehicleSearchItem, Long> {



}
