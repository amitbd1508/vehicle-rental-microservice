package com.carreservation.reservationservice.service;

import com.carreservation.reservationservice.controller.request.ReservationRequest;
import com.carreservation.reservationservice.entity.*;
import com.carreservation.reservationservice.kafka.KafkaConfig;
import com.carreservation.reservationservice.kafkamodels.BookingNotification;
import com.carreservation.reservationservice.kafkamodels.CatalogMessageDTO;
import com.carreservation.reservationservice.kafkamodels.PaymentRequestDTO;
import com.carreservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.catalog.Catalog;
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

    @Autowired
    private KafkaTemplate<String, CatalogMessageDTO> kafkaCatalogTemplate;

    @Autowired
    private KafkaTemplate<String, BookingNotification> kafkaBookingTemplate;

    @Value("${catalog.Url}")
    private String catalogUrl;

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest, String vehicleId) {
//        Account account = restTemplate.getForObject("http://localhost:8080/api/v1/users/" + reservationRequest.getAccount().getId(), Account.class);
      System.out.println(catalogUrl);
      Vehicle vehicle = restTemplate.getForObject(catalogUrl+"/" + vehicleId , Vehicle.class);


        if (vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE) {
            Reservation reservation = new Reservation();
            reservation.setDuration(reservationRequest.getDuration());
            reservation.setAccount(reservationRequest.getAccount());
            reservation.setPaymentType(reservationRequest.getPaymentType());
            reservation.setVehicle(vehicle);
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.getVehicle().setId(vehicleId);

            BookingNotification notification = new BookingNotification();
            notification.setReservationId(reservation.getId());
            notification.setUserId(reservation.getAccount().getId());
            kafkaBookingTemplate.send(KafkaConfig.TOPIC_RESERVATION_BOOKING, notification);

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


    @Override
    public String pay(PaymentRequest paymentRequest) {
        Reservation reservation = reservationRepository.findById(paymentRequest.getReservationId()).orElse(null);
        if (reservation== null||reservation.getReservationStatus().equals(ReservationStatus.CANCEL) || reservation.getReservationStatus().equals(ReservationStatus.RESERVED)) {
            System.out.println("No Reservation Found by id: " + paymentRequest.getReservationId());
            return "Not a valid Reservation";
        }

        var vehical = restTemplate.getForObject(catalogUrl+"/" + reservation.getVehicle().getId(), Vehicle.class);

        if(vehical.getVehicleStatus().equals(VehicleStatus.RESERVED)){
            return "This Vehicle is not available";
        }

        PaymentRequestDTO dto= new PaymentRequestDTO();
        dto.setUserId(reservation.getAccount().getId());
        dto.setAmount(reservation.getVehicle().getPrice());
        dto.setPaymentType(reservation.getPaymentType().toString());
        dto.setQueueId(reservation.getId());
        dto.setEmail(reservation.getAccount().getEmail());

        kafkaTemplate.send(KafkaConfig.TOPIC_NAME_RESERVATION_CREATED, dto);

        return "Processing...";

    }

    public void UpdateReservationStatus(UpdateReservationMessage updateReservationMessage){
        var reservations = reservationRepository.findById(updateReservationMessage.getReservationId()).orElse(null);
        if(updateReservationMessage.getPaymentStatus().equals("PAID")){
            if(reservations!= null){
                reservations.setReservationStatus(ReservationStatus.RESERVED);
                reservations.getVehicle().setVehicleStatus(VehicleStatus.RESERVED);
                reservationRepository.save(reservations);

                CatalogMessageDTO catalogMessageDTO = new CatalogMessageDTO();
                catalogMessageDTO.setVehicleId(reservations.getVehicle().getId());
                catalogMessageDTO.setReservationStatus(ReservationStatus.RESERVED.toString());

                kafkaCatalogTemplate.send(KafkaConfig.TOPIC_NAME_RESERVATION_CHANGED, catalogMessageDTO);
            }
        }else {
            if(reservations!= null){
                reservations.setReservationStatus(ReservationStatus.CANCEL);
                reservationRepository.save(reservations);

                CatalogMessageDTO catalogMessageDTO = new CatalogMessageDTO();
                catalogMessageDTO.setVehicleId(reservations.getVehicle().getId());
                catalogMessageDTO.setReservationStatus(VehicleStatus.AVAILABLE.toString());
                kafkaCatalogTemplate.send(KafkaConfig.TOPIC_NAME_RESERVATION_CHANGED, catalogMessageDTO);
            }
        }
    }
}
