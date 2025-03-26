package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.PackageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PackageDetailsRepository extends JpaRepository<PackageDetails, UUID> {

}
