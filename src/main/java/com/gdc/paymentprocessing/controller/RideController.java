package com.gdc.paymentprocessing.controller;

import com.gdc.paymentprocessing.entity.request.RideRequest;
import com.gdc.paymentprocessing.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/create-ride")
    public ResponseEntity<String> createRide(@RequestBody RideRequest rideRequest) {
        try {
            rideService.createRide(rideRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ride created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create ride: " + e.getMessage());
        }
    }


}
