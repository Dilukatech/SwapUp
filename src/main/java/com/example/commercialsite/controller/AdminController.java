package com.example.commercialsite.controller;

import com.example.commercialsite.dto.response.getAllUsersToAdmin;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/on-hold")
    public ResponseEntity<String> holdUser(@PathVariable Long userId) {
         return userService.holdUser(userId);
    }

    @PostMapping("/{userId}/remove-hold")
    public ResponseEntity<String> removeHoldFromUser(@PathVariable Long userId) {
         return userService.removeHoldFromUser(userId);
    }

    @GetMapping(path = "/get-all-users")
    public ResponseEntity<List<getAllUsersToAdmin>> getAllUsers(){
        return userService.getAllUsers();
    }

}
