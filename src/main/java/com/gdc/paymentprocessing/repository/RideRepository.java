package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RideRepository extends JpaRepository<Ride, UUID> {
}
