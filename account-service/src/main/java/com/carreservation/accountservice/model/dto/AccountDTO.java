package com.carreservation.accountservice.model.dto;

import com.carreservation.accountservice.model.entity.Address;
import com.carreservation.accountservice.model.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Address shippingAddress;
    private Set<PaymentMethod> paymentTypes;
    private String preferredPaymentType;

}
