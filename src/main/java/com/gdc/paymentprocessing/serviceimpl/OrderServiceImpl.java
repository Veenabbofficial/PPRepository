package com.gdc.paymentprocessing.serviceimpl;

import com.gdc.paymentprocessing.entity.request.UserRequest;
import com.gdc.paymentprocessing.repository.DriverRepository;
import com.gdc.paymentprocessing.repository.PackageDetailsRepository;
import com.gdc.paymentprocessing.repository.PaymentRepository;
import com.gdc.paymentprocessing.repository.UserRepository;
import com.gdc.paymentprocessing.entity.*;
import com.gdc.paymentprocessing.entity.request.OrderRequest;
import com.gdc.paymentprocessing.entity.response.OrderResponse;
import com.gdc.paymentprocessing.enums.OrderStatus;
import com.gdc.paymentprocessing.repository.*;
import com.gdc.paymentprocessing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PackageDetailsRepository packageDetailsRepository;


    @Override
    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        Ride ride = rideRepository.findById(orderRequest.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Driver driver = driverRepository.findById(orderRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        BigDecimal baseFare = ride.getFare();
        BigDecimal total = baseFare.add(BigDecimal.valueOf(orderRequest.getQuantity()).multiply(BigDecimal.valueOf(5)));



        Order order = new Order();
        order.setRide(ride);
        order.setUser(user);
        order.setDriver(driver);
        order.setQuantity(orderRequest.getQuantity());
        order.setDescription(orderRequest.getDescription());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPickupLocation(orderRequest.getPickupLocation());
        order.setDropoffLocation(orderRequest.getDropoffLocation());
        order.setReceiverName(orderRequest.getReceiverName());
        order.setReceiverPhone(orderRequest.getReceiverPhone());
        order.setReceiverAddress(orderRequest.getReceiverAddress());
        order.setDeliveryTimeEstimate(orderRequest.getDeliveryTimeEstimate());
        order.setTotalAmount(total);

        order.setDeliveryTimeEstimate(LocalDateTime.now().plusHours(2));

        PackageDetails packageDetails = new PackageDetails();
        packageDetails.setPackageType(Enum.valueOf(com.gdc.paymentprocessing.enums.PackageType.class, orderRequest.getPackageType()));
        packageDetails.setWeight(orderRequest.getWeight());
        packageDetails.setIsFragile(orderRequest.getIsFragile());
        packageDetails.setOrder(order);

        order.setPackageDetails(packageDetails);

        orderRepository.save(order);

        return "Order created successfully";
    }

    @Override
    public OrderResponse getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponse(order);

    }

    @Override
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public String deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }

    private OrderResponse mapToResponse(Order order){
        return new OrderResponse(order.getOrderId(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getDeliveryTimeEstimate(),
                order.getCreatedAt(),
                order.getDriver().getName(),
                order.getUser().getName());
    }
}
