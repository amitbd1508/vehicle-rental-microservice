package com.carreservation.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @Embedded
    private Address address;

    @Embedded
    private PaymentInfo paymentInfo;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Account(){
        this.id= UUID.randomUUID().toString();
    }
}
