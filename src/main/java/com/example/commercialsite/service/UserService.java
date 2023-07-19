package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.UserRegisterRequest;

public interface UserService {

    String registerUser(UserRegisterRequest userRegisterRequest);
}
