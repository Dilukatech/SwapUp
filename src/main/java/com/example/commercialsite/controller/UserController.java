package com.example.commercialsite.controller;


import com.example.commercialsite.dto.request.LoginRequest;
import com.example.commercialsite.dto.response.AuthResponse;
import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.securityConfig.JwtService;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @PostMapping(path = "/register-customer")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
//        userService.sendVerificationEmail(userRegisterRequest);
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping(path = "/register-staff")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerStaff(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping({"/login"})
    public ResponseEntity<StandardResponse> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        return jwtService.createJwtToken(loginRequest);
    }

    @GetMapping(path = "/verification",params = "code")
    public ResponseEntity<?> verifyCustomer(@RequestParam(value = "code")String code)throws Exception{
        return userService.verifyCustomer(code);
    }


}
