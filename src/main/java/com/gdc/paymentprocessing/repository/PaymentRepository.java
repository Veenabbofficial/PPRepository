package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
