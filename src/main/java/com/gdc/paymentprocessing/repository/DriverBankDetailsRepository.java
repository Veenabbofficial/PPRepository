package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.DriverBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverBankDetailsRepository extends JpaRepository<DriverBankDetails, UUID> {
}
