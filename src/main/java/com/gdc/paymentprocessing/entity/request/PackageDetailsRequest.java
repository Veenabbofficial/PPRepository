package com.gdc.paymentprocessing.entity.request;

import com.gdc.paymentprocessing.enums.PackageType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetailsRequest {
    @NotNull
    private PackageType packageType;

    @NotNull
    private Double weight;

    @NotNull
    private Boolean isFragile;

    private UUID orderId;

}
