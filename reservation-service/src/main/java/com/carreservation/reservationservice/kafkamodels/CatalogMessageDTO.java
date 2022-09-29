package com.carreservation.reservationservice.kafkamodels;

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
