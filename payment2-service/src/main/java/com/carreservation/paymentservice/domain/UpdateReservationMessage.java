package com.carreservation.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateReservationMessage {
    private String id;
    private String userId;
    private String reservationId;
    private String paymentStatus;

    public UpdateReservationMessage(){
        this.id = UUID.randomUUID().toString();
    }
}
