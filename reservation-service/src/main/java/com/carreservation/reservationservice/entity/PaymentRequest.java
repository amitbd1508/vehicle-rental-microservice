package com.carreservation.reservationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String userId;
    private String reservationId;
}
