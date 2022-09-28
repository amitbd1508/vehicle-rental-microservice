package com.carreservation.catalogservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogMessageDTO {

    private String vehicleId;
    private String reservationStatus;

}
