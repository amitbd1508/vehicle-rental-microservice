package com.carreservation.paymentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PaymentRequestDTO {
        private String paymentType;
        private String userId;
        private double amount;

        private String queueId;
        @Override
        public String toString() {
            return "PaymentRequestDTO{" +
                    "paymentType='" + paymentType + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }

