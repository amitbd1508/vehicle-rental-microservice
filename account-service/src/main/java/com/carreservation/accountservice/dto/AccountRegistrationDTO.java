package com.carreservation.accountservice.dto;

import com.carreservation.accountservice.entity.Address;
import com.carreservation.accountservice.entity.PaymentInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRegistrationDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private Address address;
    private PaymentInfo paymentInfo;
}
