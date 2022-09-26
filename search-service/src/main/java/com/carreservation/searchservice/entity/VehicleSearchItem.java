package com.carreservation.searchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.UUID;

@Document(indexName = "vehicle")
@AllArgsConstructor
@Getter
@Setter
public class VehicleSearchItem implements Serializable {
    @Id
    private String id;
    private String catalogName;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private Integer quantity;
    private String yearOfManufacture;
    private Double price;

    public VehicleSearchItem(){
        this.id= UUID.randomUUID().toString();
    }

}
