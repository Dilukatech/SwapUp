package com.example.commercialsite.controller;

import com.example.commercialsite.dto.LoginRequest;
import com.example.commercialsite.dto.LoginResponse;
import com.example.commercialsite.securityConfig.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/login"})
    public LoginResponse createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{


        return jwtService.createJwtToken(loginRequest);
    }

}