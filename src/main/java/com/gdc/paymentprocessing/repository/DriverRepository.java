package com.gdc.paymentprocessing.repository;


import com.gdc.paymentprocessing.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByVehicleNumber(String vehicleNumber);
    Optional<Driver> findByLicenseNumber(String licenseNumber);
    Optional<Driver> findByContactNumber(String mobileNumber);

}
