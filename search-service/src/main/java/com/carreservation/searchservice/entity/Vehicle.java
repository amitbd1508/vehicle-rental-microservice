package com.carreservation.searchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@AllArgsConstructor
@Repository
@Document("catalog")
@Data
public class Vehicle {
    @Id
    private String id;
    private String catalogName;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private Integer quantity;
    private String yearOfManufacture;
    private VehicleType vehicleType;
    private VehicleStatus vehicleStatus;
    private Double price;
    public Vehicle(){
        this.id= UUID.randomUUID().toString();
    }
}
