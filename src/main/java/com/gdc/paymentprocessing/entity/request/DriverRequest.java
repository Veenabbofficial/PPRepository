package com.gdc.paymentprocessing.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

    private UUID driverId;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Contact number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Contact number must be 10 digits and start with 6, 7, 8, or 9")
    private String contactNumber;
    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;
    @NotBlank(message = "License number is required")
    private String licenseNumber;

}
