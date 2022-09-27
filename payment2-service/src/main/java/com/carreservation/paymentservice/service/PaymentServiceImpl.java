package com.carreservation.paymentservice.service;

import com.carreservation.paymentservice.domain.PaymentNotification;
import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.domain.PaymentStatus;
import com.carreservation.paymentservice.domain.UpdateReservationMessage;
import com.carreservation.paymentservice.dto.PaymentRequestDTO;
import com.carreservation.paymentservice.kafka.KafkaConfig;
import com.carreservation.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public PaymentRequest makePayment(PaymentRequest request){
        request.setPaymentStatus(PaymentStatus.PAID);
        request.setPaymentType(request.getPaymentType());
        request.setUserId(request.getUserId());
        request.setPrice(request.getPrice());
        request.setReservationId(request.getReservationId());
        return paymentRepository.save(request);
    }

    @Override
    public void processPayment(PaymentRequestDTO paymentRequestDTO){
        var paymentRequest  = paymentRepository.findAll().stream().filter(p-> p.getReservationId().equals(paymentRequestDTO.getQueueId())).collect(Collectors.toList());
        if(paymentRequest!= null && paymentRequest.size()==0){
            PaymentRequest request= new PaymentRequest();
            request.setPaymentType(paymentRequestDTO.getPaymentType());
            request.setUserId(paymentRequestDTO.getUserId());
            request.setPrice(paymentRequestDTO.getAmount());
            request.setReservationId(paymentRequestDTO.getQueueId());
            var request2 = makePayment(request);

            // payment notification
            PaymentNotification py = new PaymentNotification();
            py.setPaymentStatus(PaymentStatus.PAID.toString());
            py.setUserId(request.getUserId());
            py.setUserEmail(paymentRequestDTO.getEmail());
            py.setReservationId(request.getReservationId());

            kafkaTemplate.send(KafkaConfig.PAYMENT_NOTIFICATION, py);

            UpdateReservationMessage rm= new UpdateReservationMessage();
            rm.setPaymentStatus(PaymentStatus.PAID.toString());
            rm.setUserId(request.getUserId());
            rm.setReservationId(request.getReservationId());
            kafkaTemplate.send(KafkaConfig.UPDATE_RES_STATUS, rm);

        }
    }
}