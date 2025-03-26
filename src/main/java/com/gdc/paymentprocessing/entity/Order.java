package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", updatable = false, nullable = false)
    private UUID orderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @OneToOne(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToOne(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private PackageDetails packageDetails;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @Column(precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @NotBlank(message = "Pickup location is required")
    @Size(max = 255, message = "Pickup location cannot exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String pickupLocation;

    @NotBlank(message = "Drop location is required")
    @Size(max = 255, message = "Drop location cannot exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String dropoffLocation;

    @NotBlank(message = "Receiver name cannot be empty")
    @Size(max = 100, message = "Receiver name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String receiverName;

    @NotBlank(message = "Receiver phone cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Receiver phone must be a valid 10-digit number")
    @Column(nullable = false, length = 10)
    private String receiverPhone;

    @NotBlank(message = "Receiver address cannot be empty")
    @Size(max = 500, message = "Receiver address cannot exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String receiverAddress;

    @Column(name = "delivery_time_estimate")
    private LocalDateTime deliveryTimeEstimate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Business methods
    public void addPayment(Payment payment) {
        payment.setOrder(this);
        this.payment = payment;
    }

    public void addPackageDetails(PackageDetails packageDetails) {
        packageDetails.setOrder(this);
        this.packageDetails = packageDetails;
    }
}