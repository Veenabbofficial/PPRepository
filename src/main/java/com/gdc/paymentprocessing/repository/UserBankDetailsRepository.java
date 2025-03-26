package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.UserBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBankDetailsRepository extends JpaRepository<UserBankDetails, UUID> {
}
