package com.carreservation.reservationservice.controller;

import com.carreservation.reservationservice.entity.Reservation;
import com.carreservation.reservationservice.controller.request.ReservationRequest;
import com.carreservation.reservationservice.entity.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.carreservation.reservationservice.service.ReservationService;

import java.util.List;

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

//    @PutMapping("/{id}")
//    public Reservation updateStatus(@PathVariable Long id, @RequestBody VehicleStatus status){
//        return reservationService.updateStatus(id, status);
//    }

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest paymentRequest){
        return reservationService.pay(paymentRequest);
    }

}
