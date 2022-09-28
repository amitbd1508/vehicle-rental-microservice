package com.carreservation.searchservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.UUID;

@Document(indexName = "vehicle")
@AllArgsConstructor
@Data
public class VehicleSearchItem implements Serializable {
    @Id
    private String id;
    private String catalogName;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private String yearOfManufacture;
    private Double price;

    public VehicleSearchItem(){
        this.id= UUID.randomUUID().toString();
    }

}
