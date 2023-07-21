package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.entity.User;

import java.util.Optional;

public interface UserService {

    String registerUser(UserRegisterRequest userRegisterRequest);

    Optional<User> getUserById(Long userId);
}
