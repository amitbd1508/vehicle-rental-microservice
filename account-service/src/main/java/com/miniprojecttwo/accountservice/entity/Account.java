package com.miniprojecttwo.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @Embedded
    private Address shippingAddress;

    @ManyToMany
    @JoinTable(name = "account_payment_method",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "payment_id")}
    )
    private Set<PaymentMethod> paymentMethods;

    private String preferredPaymentMethod;


}
