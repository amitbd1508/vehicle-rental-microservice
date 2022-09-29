package com.carreservation.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @Column(length = 50)
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @Embedded
    private Address address;

    @Embedded
    private com.carreservation.accountservice.entity.PaymentInfo paymentInfo;

    @Enumerated(EnumType.STRING)
    private com.carreservation.accountservice.entity.Roles role;

    public Account(){
        this.id= UUID.randomUUID().toString();
    }
}
