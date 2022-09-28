package com.carreservation.reservationservice.service;

import com.carreservation.reservationservice.controller.request.ReservationRequest;
import com.carreservation.reservationservice.entity.*;
import com.carreservation.reservationservice.kafka.KafkaConfig;
import com.carreservation.reservationservice.kafkamodels.PaymentRequestDTO;
import com.carreservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate;

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest, String vehicleId) {
//        Account account = restTemplate.getForObject("http://localhost:8080/api/v1/users/" + reservationRequest.getAccount().getId(), Account.class);
        Vehicle vehicle = new Vehicle(); //restTemplate.getForObject("http://localhost:9001/vehicles/getvehicle/" + vehicleId, Vehicle.class);

        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicle.setId("string-id");
        vehicle.setBrand("Ford");
        vehicle.setVehicleType(VehicleType.SEDAN);
        vehicle.setColor("Silver");
        vehicle.setPrice(62.55);

        if (vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE) {
            Reservation reservation = new Reservation();
            reservation.setDuration(reservationRequest.getDuration());
            reservation.setAccount(reservationRequest.getAccount());
            reservation.setPaymentType(reservationRequest.getPaymentType());
//            vehicle.setId(null);
            reservation.setVehicle(vehicle);
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.getVehicle().setVehicleStatus(VehicleStatus.RESERVED);
            reservation.getVehicle().setId(vehicleId);
            //restTemplate.put("http://localhost:9001/vehicles/update-status/" + vehicleId, VehicleStatus.RESERVED, VehicleStatus.class);
            PaymentRequestDTO dto= new PaymentRequestDTO();
            dto.setUserId(reservation.getAccount().getId());
            dto.setAmount(reservation.getVehicle().getPrice());
            dto.setPaymentType(reservation.getPaymentType().toString());
            dto.setQueueId(reservation.getId());
            dto.setEmail(reservationRequest.getAccount().getEmail());

            kafkaTemplate.send(KafkaConfig.TOPIC_NAME_RESERVATION_CREATED, dto);

            return reservationRepository.save(reservation);
        } else
            System.out.println("Vehicle is not available");
        return null;

    }

    @Override
    public void cancelReservation(String reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (!optionalReservation.isPresent()) {
            System.out.println("Reservation does not exist");
        } else {
            Reservation reservation = optionalReservation.get();
            reservation.getVehicle().setVehicleStatus(VehicleStatus.AVAILABLE);
            reservationRepository.deleteById(reservationId);
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

//    @Override
//    public Reservation updateStatus(Long reservationId, VehicleStatus status) {
//        Reservation reservation= reservationRepository.findById(reservationId).get();
//        reservation.setReservationStatus(ReservationStatus.RESERVED);
//        return reservation;
//    }

    @Override
    public String pay(PaymentRequest paymentRequest) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(paymentRequest.getReservationId());
        if (!optionalReservation.isPresent()) {
            System.out.println("No Reservation Found by id: " + paymentRequest.getReservationId());
            return "Reservation is Not Successful";
        }

        Double price = optionalReservation.get().getVehicle().getPrice();

        LocalDate startDate = optionalReservation.get().getDuration().getRentalDate();
        LocalDate endDate = optionalReservation.get().getDuration().getReturnDate();
        Double days = (double) ChronoUnit.DAYS.between(startDate, endDate);
        Double totalPrice = price * days;
        paymentRequest.setTotalPrice(totalPrice);
        paymentRequest.setPaymentStatus(PaymentStatus.PENDING);

        PaymentRequest paymentRequest1 = restTemplate.postForObject("http://localhost:9041/payments", paymentRequest, PaymentRequest.class);

        if(paymentRequest1.getPaymentStatus() == PaymentStatus.PAID){
            optionalReservation.get().setReservationStatus(ReservationStatus.RESERVED);
            return "Payment processed successfully, reservation confirmed!";
        } else {
            return "Payment request failed, please try again!";
        }
    }

    public void UpdateReservationStatus(UpdateReservationMessage updateReservationMessage){
        var reservations = reservationRepository.findById(updateReservationMessage.getReservationId()).orElse(null);
        if(updateReservationMessage.getPaymentStatus().equals("PAID")){
            if(reservations!= null){
                reservations.setReservationStatus(ReservationStatus.RESERVED);
                reservationRepository.save(reservations);
            }
        }else {
            if(reservations!= null){
                reservations.setReservationStatus(ReservationStatus.CANCEL);
                reservationRepository.save(reservations);
            }
        }
    }
}
