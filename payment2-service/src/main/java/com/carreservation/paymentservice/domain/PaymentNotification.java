package com.carreservation.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class PaymentNotification {
    private String id;
    private String userId;
    private String reservationId;
    private String paymentStatus;
    private String userEmail;
    public PaymentNotification(){
        this.id = UUID.randomUUID().toString();
    }
}
