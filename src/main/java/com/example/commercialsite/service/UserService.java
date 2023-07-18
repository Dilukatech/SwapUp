package com.example.commercialsite.service;

import com.example.commercialsite.dto.CustomerRegisterRequest;
import com.example.commercialsite.entity.User;

public interface UserService {

    String registerCustomer(CustomerRegisterRequest customerRegisterRequest);
}
