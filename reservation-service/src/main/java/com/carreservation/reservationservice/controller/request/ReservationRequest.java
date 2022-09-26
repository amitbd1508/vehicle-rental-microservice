package com.carreservation.reservationservice.controller.request;


import com.carreservation.reservationservice.entity.Account;
import com.carreservation.reservationservice.entity.Duration;
import com.carreservation.reservationservice.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private Account account;
    private Duration duration;
    private PaymentType paymentType;
}
