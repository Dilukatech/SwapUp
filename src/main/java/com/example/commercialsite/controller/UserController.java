package com.example.commercialsite.controller;


import com.example.commercialsite.dto.LoginRequest;
import com.example.commercialsite.dto.LoginResponse;
import com.example.commercialsite.dto.UserRegisterRequest;
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


    @PostMapping(path = "/register-customer")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest)throws Exception{
        return new ResponseEntity<>(userService.registerUser(userRegisterRequest), HttpStatus.CREATED);
    }

    @PostMapping(path = "/register-staff")
    public ResponseEntity<String> registerStaff(@RequestBody UserRegisterRequest userRegisterRequest)throws Exception{
        return new ResponseEntity<>(userService.registerUser(userRegisterRequest), HttpStatus.CREATED);
    }


    @PostMapping({"/login"})
    public ResponseEntity<LoginResponse> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{
        return new ResponseEntity<>(jwtService.createJwtToken(loginRequest),HttpStatus.OK);
    }


}
