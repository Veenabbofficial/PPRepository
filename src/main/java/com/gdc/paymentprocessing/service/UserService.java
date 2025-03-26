package com.gdc.paymentprocessing.service;

import com.gdc.paymentprocessing.entity.User;
import com.gdc.paymentprocessing.entity.request.UserRequest;

public interface UserService {
    public String addUser(UserRequest userRequest);
    public boolean emailExists(String email);


}
