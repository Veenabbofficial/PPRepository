package com.gdc.paymentprocessing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "driver_bank_details",schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_bank_details_id", updatable = false, nullable = false)
    private UUID driverBankDetailsId;

    @OneToOne(mappedBy = "driverBankDetails",cascade = CascadeType.ALL)
    //@JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Pattern(regexp = "\\d+", message = "Account number must be numeric")
    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;


    @Column(name = "ifsc_code", nullable = false, length = 20)
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC Code format")
    private String ifscCode;

    @Column(name = "bank_name", nullable = false, length = 255)
    private String bankName;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt ;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @PrePersist
    private void onCreate() {
        if (createdAt==null){
            createdAt=LocalDateTime.now();
        }
        processedAt=LocalDateTime.now();

    }

}
