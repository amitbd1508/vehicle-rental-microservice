package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.model.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CatalogRepo extends MongoRepository<Vehicle, String> {

    @Query(value = "{'brand': {$regex : ?0, $options: 'i'}}")
    Page<Vehicle> getByBrand(
            String brand,
            Pageable pageable
    );


    @Query(value = "{'model': {$regex : ?0, $options: 'i'}}")
    Page<Vehicle> getByModel(String model, Pageable pageable);

}
