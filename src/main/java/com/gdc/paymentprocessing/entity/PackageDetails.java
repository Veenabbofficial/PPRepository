package com.gdc.paymentprocessing.entity;

import com.gdc.paymentprocessing.enums.PackageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "packages",schema = "gdc-db")
@AllArgsConstructor
public class PackageDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id", updatable = false, nullable = false)
    private UUID packageId;

    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 100)
    private PackageType packageType;

    @Column(nullable = false)
    private Double weight;

    @Column(name = "is_fragile")
    private Boolean isFragile;

}
