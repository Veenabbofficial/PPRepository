package com.gdc.paymentprocessing.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
        private UUID userId;
        private String name;
        private Integer age;
        private String gender;
        private String email;
        private String password;
        private String contactNumber;
        private String address;
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;





}
