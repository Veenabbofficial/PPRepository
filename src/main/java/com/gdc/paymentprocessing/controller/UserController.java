package com.gdc.paymentprocessing.controller;

import com.gdc.paymentprocessing.entity.User;
import com.gdc.paymentprocessing.entity.request.UserRequest;
import com.gdc.paymentprocessing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create_users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequest userRequest, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        if (userService.emailExists(userRequest.getEmail())){
            return ResponseEntity.badRequest().body("User Already exists");
        }
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added Successfully");

    }

}
