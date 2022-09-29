package com.carreservation.reservationservice.controller;

import com.carreservation.reservationservice.entity.Reservation;
import com.carreservation.reservationservice.controller.request.ReservationRequest;
import com.carreservation.reservationservice.entity.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carreservation.reservationservice.service.ReservationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
public class ReservationController {
    //@Qualifier("reservationServiceImpl")
    @Autowired
    ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PostMapping("/{vehicleId}")
    public Reservation createReservation(@RequestBody ReservationRequest reservationRequest, @PathVariable String vehicleId){
        return reservationService.addReservation(reservationRequest, vehicleId);
    }

    @DeleteMapping("/{id}")
    public void cancelReservation( @PathVariable String id ){
        reservationService.cancelReservation(id);
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id){
        return reservationService.getReservationById(id);
    }

    @GetMapping("/getUserReservation/{userId}")
    public List<Reservation> getUsersReservation(@PathVariable String userId){
        return reservationService.getAllReservations().stream().filter(r->r.getAccount().getId().equals(userId)).collect(Collectors.toList());
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(reservationService.pay(paymentRequest));
    }

}
