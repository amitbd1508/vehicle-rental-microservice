package com.carreservation.accountservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class PaymentInfo {
    private String paymentType;
    private String paymentMethodIdentityNumber;
}
