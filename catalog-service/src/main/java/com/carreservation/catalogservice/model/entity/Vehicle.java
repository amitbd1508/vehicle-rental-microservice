package com.carreservation.catalogservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Document("catalog")
@Data
public class Vehicle implements Serializable {
    @Id
    private String id;
    private String catalogName;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private String yearOfManufacture;
    private VehicleType vehicleType;
    private VehicleStatus vehicleStatus;
    private Double price;

    public Vehicle(String catalogName, String plateNumber, String brand, String model, String color, String yearOfManufacture, VehicleType vehicleType, VehicleStatus vehicleStatus, Double price) {
        this.catalogName = catalogName;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.vehicleType = vehicleType;
        this.vehicleStatus = vehicleStatus;
        this.price = price;
    }
}
