package com.carreservation.paymentservice.repository;

import com.carreservation.paymentservice.domain.PaymentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPaymentRepository  extends MongoRepository<PaymentRequest, String> {
}
