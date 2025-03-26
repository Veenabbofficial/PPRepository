package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.RefundMethod;
import com.gdc.paymentprocessing.enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refunds",schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Refund.transaction", attributeNodes = @NamedAttributeNode("transaction"))

public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refund_id", updatable = false, nullable = false)
    private UUID refundId;

    @OneToOne
    @JoinColumn(name = "transaction_id",nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal refundAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RefundStatus refundStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)
    private RefundMethod refundMethod;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt ;

    private LocalDateTime processedAt;
    @PreUpdate
    private void onUpdate(){
        this.processedAt=LocalDateTime.now();
    }
}
