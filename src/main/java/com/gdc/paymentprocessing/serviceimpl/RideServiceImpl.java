package com.gdc.paymentprocessing.serviceimpl;

import com.gdc.paymentprocessing.entity.Driver;
import com.gdc.paymentprocessing.entity.Ride;
import com.gdc.paymentprocessing.entity.request.RideRequest;
import com.gdc.paymentprocessing.entity.response.RideResponse;
import com.gdc.paymentprocessing.enums.RideStatus;
import com.gdc.paymentprocessing.repository.DriverRepository;
import com.gdc.paymentprocessing.repository.RideRepository;
import com.gdc.paymentprocessing.service.RideService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;

    private final DriverRepository driverRepository;

    public RideServiceImpl(RideRepository rideRepository, DriverRepository driverRepository) {
        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public RideResponse createRide(RideRequest rideRequest) {
        if (rideRequest.getPickupLocation() == null || rideRequest.getDropLocation() == null) {
            throw new IllegalArgumentException("Pickup and drop locations must be provided");
        }
        Driver driver = driverRepository.findById(rideRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        BigDecimal baseFare = new BigDecimal("100");
        int distanceKm = calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropLocation()); // dummy logic
        BigDecimal fare = baseFare.add(new BigDecimal(distanceKm * 20));

        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setPickupLocation(rideRequest.getPickupLocation());
        ride.setDropLocation(rideRequest.getDropLocation());
        ride.setDepartureTime(
                rideRequest.getDepartureTime() != null ? rideRequest.getDepartureTime() : LocalDateTime.now()
        );

        ride.setFare(fare);
        ride.setStatus(RideStatus.PENDING);

        ride.setStartTime(LocalDateTime.now()); // <-- Set start time as now
        ride.setEndTime(null);

        Ride saved = rideRepository.save(ride);

        return new RideResponse("Ride created successfully", saved.getRideId(), saved.getFare());

    }

    private int calculateDistance(String pickup, String drop) {
        return 10;
    }
}
