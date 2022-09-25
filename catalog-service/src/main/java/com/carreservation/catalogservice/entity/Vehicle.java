package com.carreservation.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public Vehicle(String catalogName, String plateNumber, String brand, String model, String color, Integer quantity, String yearOfManufacture, VehicleType vehicleType, VehicleStatus vehicleStatus, Double price) {
        this.catalogName = catalogName;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.quantity = quantity;
        this.yearOfManufacture = yearOfManufacture;
        this.vehicleType = vehicleType;
        this.vehicleStatus = vehicleStatus;
        this.price = price;
    }
}
