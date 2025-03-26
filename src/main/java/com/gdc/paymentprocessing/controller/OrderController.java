package com.gdc.paymentprocessing.controller;

import com.gdc.paymentprocessing.entity.request.OrderRequest;
import com.gdc.paymentprocessing.entity.response.OrderResponse;
import com.gdc.paymentprocessing.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        String message = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderId) {
      return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId){
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }

}
