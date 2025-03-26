package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rides",schema = "gdc-db" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ride_id",updatable = false, nullable = false)
    private UUID rideId;


    @OneToOne(mappedBy = "ride")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "driver_id",nullable = false)
    private Driver driver;

    @Column(name = "pickup_location", nullable = false)
    private String pickupLocation;

    @Column(name = "drop_location", nullable = false)
    private String dropLocation;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(columnDefinition = "double precision")
    private BigDecimal fare;


    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private RideStatus status=RideStatus.PENDING;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;
}


/*
id
pickup location
drop
departuretime
fare
 */
