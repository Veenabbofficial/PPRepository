package com.gdc.paymentprocessing.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequest {

    private UUID driverId;

    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime departureTime;
    /*private Long driverId;
    private String pickupLocation;
    private String dropLocation;
    private Long orderId;
    private BigDecimal fare;
    private LocalDateTime departureTime;*/
}
