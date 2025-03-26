package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.TransactionStatus;
import com.gdc.paymentprocessing.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions",schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false) // Store as UUID in the database
    private UUID transactionId;

    @PrePersist
    public void prePersist() {
        if (transactionId == null) {
            transactionId = UUID.randomUUID(); // Generate UUID before saving
        }
        this.processedAt = LocalDateTime.now(); // Set processedAt time
    }

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false, updatable = false, unique = true)
    private String transactionIdReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_bank_id", nullable = false)
    private UserBankDetails userBankDetails;

    @ManyToOne
    @JoinColumn(name = "driver_bank_id", nullable = false)
    private DriverBankDetails driverBankDetails;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;
}

    /*
    transactionid
    mobile number
    receiver bank details
    sender bank details
    timestamp
    transaction status

     */

