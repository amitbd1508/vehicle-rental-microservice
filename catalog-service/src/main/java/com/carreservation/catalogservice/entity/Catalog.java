package com.carreservation.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Document("catalog")
@Data
@AllArgsConstructor
@Repository
public class Catalog {
    @Id
    private String id;
    private String name;

    private String plateNumber;

    private String brand;

    private String model;

    private String color;

    private Integer quantity;

    private String yearOfManufacture;

    private VehicleType vehicleType;

    private VehicleStatus vehicleStatus;

    private Double price;
    public Catalog(){
        this.id=UUID.randomUUID().toString();
    }
}
