package com.gdc.paymentprocessing.entity.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private UUID orderId;

    @NotNull
    private UUID rideId;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID driverId;

    private String packageType;
    private Double weight;
    private Boolean isFragile;

    //private Long packageDetailsId;
   /* @NotNull
    private PackageDetails packageDetails;*/
    @NotBlank
    private String pickupLocation;
    @NotBlank
    private String dropoffLocation;
    @NotNull
    @Min(1)
    private Integer quantity;

    //private Double totalAmount;
    private LocalDateTime deliveryTimeEstimate;

    private String description;
    @NotBlank
    private String receiverName;
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String receiverPhone;
    @NotBlank
    private String receiverAddress;


    //private Double totalAmount;

    //private OrderStatus orderStatus;

    }

