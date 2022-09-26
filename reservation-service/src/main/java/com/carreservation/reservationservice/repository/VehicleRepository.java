package com.carreservation.reservationservice.repository;

import com.carreservation.reservationservice.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
