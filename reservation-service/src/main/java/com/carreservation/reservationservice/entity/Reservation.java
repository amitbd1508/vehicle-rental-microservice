package com.carreservation.reservationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Document
@Data
@AllArgsConstructor
public class Reservation {

    @Id
    private String id;
    // private String accountId;
    private ReservationStatus reservationStatus;

    Account account;
    //    @Embedded
    private Duration duration;
    private PaymentType paymentType;

    //    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "vehicleID")
    private Vehicle vehicle;
    public Reservation(){
        this.id= UUID.randomUUID().toString();
    }
}
