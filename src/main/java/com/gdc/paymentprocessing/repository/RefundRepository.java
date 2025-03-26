package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefundRepository extends JpaRepository<Refund, UUID> {
}
