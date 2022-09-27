package com.carreservation.reservationservice.kafkamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private String paymentType;
    private String userId;
    private double amount;

    private String queueId;

    private String email;
    @Override
    public String toString() {
        return "PaymentRequestDTO{" +
                "paymentType='" + paymentType + '\'' +
                ", userId='" + userId + '\'' +
                ", queueId='" + queueId + '\'' +
                '}';
    }
}
