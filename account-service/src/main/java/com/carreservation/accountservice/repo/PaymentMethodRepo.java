package com.carreservation.accountservice.repo;

import com.carreservation.accountservice.model.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod,Long> {
    Optional<PaymentMethod> getPaymentMethodByName(String name);
}
