package com.gdc.paymentprocessing.service;

import com.gdc.paymentprocessing.entity.request.OrderRequest;
import com.gdc.paymentprocessing.entity.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    String createOrder(OrderRequest request);
    OrderResponse getOrderById(UUID id);
    List<OrderResponse> getAllOrders();
    String deleteOrder(UUID id);
}
