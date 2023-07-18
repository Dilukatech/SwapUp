package com.example.commercialsite.controller;


import com.example.commercialsite.dto.CustomerRegisterRequest;
import com.example.commercialsite.dto.LoginRequest;
import com.example.commercialsite.dto.LoginResponse;
import com.example.commercialsite.securityConfig.JwtService;
import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerRegisterRequest customerRegisterRequest)throws Exception{
//        String massage = userService.registerCustomer(customerRegisterRequest);
        return new ResponseEntity<>(userService.registerCustomer(customerRegisterRequest), HttpStatus.CREATED);
    }


    @PostMapping({"/login"})
    public ResponseEntity<LoginResponse> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{
        return new ResponseEntity<>(jwtService.createJwtToken(loginRequest),HttpStatus.OK);
    }


}
