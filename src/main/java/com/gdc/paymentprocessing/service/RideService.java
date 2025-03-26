package com.gdc.paymentprocessing.service;

import com.gdc.paymentprocessing.entity.request.RideRequest;
import com.gdc.paymentprocessing.entity.response.RideResponse;

public interface RideService {
    RideResponse createRide(RideRequest rideRequest);
}
