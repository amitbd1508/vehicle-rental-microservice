package com.carreservation.catalogservice.model.dto;

import com.carreservation.catalogservice.model.entity.VehicleStatus;
import com.carreservation.catalogservice.model.entity.VehicleType;
import lombok.Data;

@Data
public class VehicleRequestDto {
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
}
