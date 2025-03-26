package com.gdc.paymentprocessing.enums;

public enum RideStatus {
    PENDING,        // Ride is created but not yet started
    ONGOING,        // Ride is currently happening
    COMPLETED,      // Ride is finished successfully
    CANCELLED,      // Ride was cancelled
    FAILED         // Ride failed due to some issue
}
