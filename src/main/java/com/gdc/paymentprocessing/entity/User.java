package com.gdc.paymentprocessing.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table(name = "users",schema = "gdc-db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id",updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @Column(name = "age",nullable = false)
    private Integer age;

    @Column(name = "gender",nullable = false)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Email(message = "Invalid email format")
    @Column(unique = true,nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Builder.Default
    @Column(name = "is_verified",nullable = false)
    private boolean isVerified = false;

    @Column(name = "contact_number",nullable = false,unique = true,length = 10)
    @NotBlank(message = "Contact number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Contact number must contain exactly 10 digits")
    private String contactNumber;

    @Column(nullable = false)
    private String address;

    @Column(name = "street",nullable = false)
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserBankDetails userBankDetails;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY   )
    private List<Order> orders;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    private String firstName;
    private String lastName;


    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null){
            createdAt = LocalDateTime.now();
        }
        if (processedAt == null){
            processedAt = LocalDateTime.now();
        }


    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


}
