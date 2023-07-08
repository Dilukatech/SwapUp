package com.example.commercialsite.controller;

import com.example.commercialsite.entity.User;
import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){

        String massage = userService.registerUser(user);
        return new ResponseEntity<>(massage , HttpStatus.CREATED);

    }

}
