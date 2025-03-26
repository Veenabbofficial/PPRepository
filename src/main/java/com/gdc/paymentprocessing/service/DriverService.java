package com.gdc.paymentprocessing.service;

import com.gdc.paymentprocessing.entity.request.DriverRequest;

import java.util.UUID;

public interface DriverService {

    public String addDriver(DriverRequest driverRequest);
    boolean driverExists(String email);
    public String updateDriver(UUID driverId, DriverRequest driverRequest);
}
