package com.carreservation.paymentservice.repository;

import com.carreservation.paymentservice.domain.PaymentRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentRequest, String> {
}
