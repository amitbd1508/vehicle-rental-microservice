package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.entity.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepo extends MongoRepository<Catalog, Integer> {
}
