package com.gdc.paymentprocessing.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor

public class RideResponse {

    private String message;
    private UUID rideId;
    private BigDecimal fare;

    public RideResponse(String message, UUID rideId, BigDecimal fare) {
        this.message = message;
        this.rideId = rideId;
        this.fare = fare;
    }

    /*private Long rideId;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime departureTime;
    private BigDecimal fare;
    private RideStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;*/


}
