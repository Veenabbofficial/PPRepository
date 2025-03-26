package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.AvailabilityStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "drivers",schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or remove this line entirely
    @Column(name = "driver_id", updatable = false, nullable = false)
    private UUID driverId;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    //@Size(min = 10, max = 10, message = "Contact number must be exactly 10 digits")
    //@Column(name = "contact_number",nullable = false,unique = true)
    //@NotBlank(message = "Contact number is required")
    //@Pattern(regexp = "\\d{10}", message = "Contact number must contains exactly 10 digits")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Contact number must be 10 digits and start with 6, 7, 8, or 9")

    private String contactNumber;

    @Column(name = "vehicle_number",nullable = false,unique = true,length = 20)
    @NotBlank(message = "Vehicle number is required")
    @Pattern(regexp = "^[A-Z0-9-]+$",message = "Vehicle number must " +
            "contain only uppercase letters,numbers and hyphens")
    private String vehicleNumber;

    @Column(nullable = false,unique = true)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false, length = 20)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_bank_details_id")
    private DriverBankDetails driverBankDetails;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ride> ride;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private String firstName;
    private String lastName;


    @PrePersist
    protected void onCreate(){
        if (createdAt == null){
            createdAt=LocalDateTime.now();
        }
    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }



}
