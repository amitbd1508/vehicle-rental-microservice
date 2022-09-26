package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface CatalogRepo extends MongoRepository<Vehicle, String> {

    @Query(value = "{'brand': {$regex : ?0, $options: 'i'}}")
    List<Vehicle> getByBrand(
            String brand
    );


    @Query(value = "{'model': {$regex : ?0, $options: 'i'}}")
    List<Vehicle> getByModel(String model);
    }
