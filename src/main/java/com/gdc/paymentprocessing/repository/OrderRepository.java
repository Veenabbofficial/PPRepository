package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
