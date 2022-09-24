package com.miniprojecttwo.accountservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class Address {

    private String city;
    private String state;
    private String streetNumber;
    private String zip;
}
