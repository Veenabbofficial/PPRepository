package com.gdc.paymentprocessing.entity.response;

import com.gdc.paymentprocessing.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID orderId;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    private LocalDateTime deliveryTimeEstimate;

    private LocalDateTime createdAt;

    private String driverName;

    private String userName;

}
