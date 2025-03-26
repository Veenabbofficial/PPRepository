package com.gdc.paymentprocessing.repository;

import com.gdc.paymentprocessing.entity.User;
import com.gdc.paymentprocessing.entity.request.UserRequest;
import com.gdc.paymentprocessing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public UserRequest findByEmail(String email);

}
