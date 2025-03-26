package com.gdc.paymentprocessing.controller;

import com.gdc.paymentprocessing.entity.request.DriverRequest;
import com.gdc.paymentprocessing.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;


    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add-driver")
    public ResponseEntity<String> addDriver(@Valid @RequestBody DriverRequest driverRequest){

        if (driverService.driverExists(driverRequest.getEmail())){
            return ResponseEntity.badRequest().body("Driver with this email already exists");
        }
        String result=driverService.addDriver(driverRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkDriverExists(@RequestParam String email){
        boolean exists = driverService.driverExists(email);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/update-driver/{id}")
    public  ResponseEntity<String> updateDriver(@PathVariable UUID id, @Valid @RequestBody DriverRequest driverRequest){
        String result = driverService.updateDriver(id,driverRequest);
        return ResponseEntity.ok(result);
    }


}
