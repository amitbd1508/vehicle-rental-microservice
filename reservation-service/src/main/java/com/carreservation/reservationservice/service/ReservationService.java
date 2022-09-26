package com.carreservation.reservationservice.service;

import com.carreservation.reservationservice.controller.request.ReservationRequest;
import com.carreservation.reservationservice.entity.PaymentRequest;
import com.carreservation.reservationservice.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation addReservation(ReservationRequest reservationRequest, String vehicleId);

    void cancelReservation(String reservationId);

    List<Reservation> getAllReservations();

    Reservation getReservationById(String reservationId);

//    Reservation updateStatus (Long reservationId, VehicleStatus status);

    String pay (PaymentRequest paymentRequest);
}
