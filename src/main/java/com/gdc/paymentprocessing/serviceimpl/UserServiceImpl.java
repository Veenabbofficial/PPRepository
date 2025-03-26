package com.gdc.paymentprocessing.serviceimpl;

import com.gdc.paymentprocessing.entity.User;
import com.gdc.paymentprocessing.entity.request.UserRequest;
import com.gdc.paymentprocessing.repository.UserRepository;
import com.gdc.paymentprocessing.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    @Override
    public String addUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()) // to be encoded below
                .contactNumber(userRequest.getContactNumber())
                .address(userRequest.getAddress())
                .street(userRequest.getStreet())
                .city(userRequest.getCity())
                .state(userRequest.getState())
                .zipCode(userRequest.getZipCode())
                .country(userRequest.getCountry())
                .createdAt(LocalDateTime.now())
                .processedAt(LocalDateTime.now())
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }

    @Override
    public boolean emailExists(String email) {
        if (userRepository.findByEmail(email) == null){
         return false;
     }
     else {
         return true;
     }
    }
}
