package com.carreservation.accountservice.dto;

import com.carreservation.accountservice.entity.Address;
import com.carreservation.accountservice.entity.PaymentInfo;
import com.carreservation.accountservice.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Address address;
    private PaymentInfo paymentInfo;
    private Roles role;

}
