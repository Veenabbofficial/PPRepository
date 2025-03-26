package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.PaymentMethod;
import com.gdc.paymentprocessing.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "payment", schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // Changed to UUID for PostgreSQL
    @Column(name = "payment_id", updatable = false, nullable = false)
    private UUID paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private Double totalAmount;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2) DEFAULT 0.0")
    private Double discountAmount = 0.0;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2) DEFAULT 0.0")
    private Double commissionAmount = 0.0;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private Double checkoutAmount;

    @Column(nullable = false, length = 255)
    private String pickupLocation;

    @Column(nullable = false, length = 255)
    private String dropLocation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        calculateCheckoutAmount();
    }

    @PreUpdate
    public void calculateCheckoutAmount() {
        double discount = Optional.ofNullable(discountAmount).orElse(0.0);
        double commission = Optional.ofNullable(commissionAmount).orElse(0.0);
        double total = Optional.ofNullable(totalAmount).orElse(0.0);

        this.checkoutAmount = total - discount - commission;
    }
}