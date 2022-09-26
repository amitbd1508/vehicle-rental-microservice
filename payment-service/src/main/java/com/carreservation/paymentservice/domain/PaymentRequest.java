package com.carreservation.paymentservice.domain;

import com.carreservation.paymentservice.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaymentRequest {
    @Id
    private String id;
    private String paymentType;
    private String reservationId;
    private String userId;
    private Double price;
    private PaymentStatus paymentStatus;

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}