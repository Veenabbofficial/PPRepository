package com.gdc.paymentprocessing.serviceimpl;

import com.gdc.paymentprocessing.entity.Driver;
import com.gdc.paymentprocessing.entity.request.DriverRequest;
import com.gdc.paymentprocessing.enums.AvailabilityStatus;
import com.gdc.paymentprocessing.exception.ResourceNotFoundException;
import com.gdc.paymentprocessing.repository.DriverRepository;
import com.gdc.paymentprocessing.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {


    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @Override
    public String addDriver(DriverRequest driverRequest) {
        if (driverRepository.findByEmail(driverRequest.getEmail()).isPresent()){
            return "Driver with this email already exists.";
        }
        if (driverRepository.findByVehicleNumber(driverRequest.getVehicleNumber()).isPresent()) {
            return "Driver with this vehicle number already exists.";
        }
        if (driverRepository.findByLicenseNumber(driverRequest.getLicenseNumber()).isPresent()) {
            return "Driver with this license number already exists.";
        }
        if (driverRepository.findByContactNumber(driverRequest.getContactNumber()).isPresent()){
            return "Driver with this contact number already exists";
        }
        Driver driver=new Driver();
       //Driver driver=new Driver();
        driver.setName(driverRequest.getName());
        driver.setEmail(driverRequest.getEmail());
        driver.setContactNumber(driverRequest.getContactNumber());
        driver.setVehicleNumber(driverRequest.getVehicleNumber());
        driver.setLicenseNumber(driverRequest.getLicenseNumber());
        driver.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);

        driverRepository.save(driver);
        return "Driver added Successfully";
    }

    @Override
    public boolean driverExists(String email) {
        return driverRepository.findByEmail(email).isPresent();
    }

    @Override
    public String updateDriver(UUID driverId, DriverRequest driverRequest) {
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() ->new ResourceNotFoundException("Driver not found with ID: " + driverId));
        existingDriver.setName(driverRequest.getName());
        existingDriver.setEmail(driverRequest.getEmail());
        existingDriver.setContactNumber(driverRequest.getContactNumber());
        existingDriver.setLicenseNumber(driverRequest.getLicenseNumber());
        existingDriver.setVehicleNumber(driverRequest.getVehicleNumber());

        driverRepository.save(existingDriver);
    return " Driver Updated Successfully";
    }
}
