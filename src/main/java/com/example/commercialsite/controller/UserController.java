package com.example.commercialsite.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    @GetMapping("/user-account")
    public String GetDetails(){

        return "get details";
    }
    @GetMapping("/test")
    public String testDetails(){

        return "test details";
    }
}
