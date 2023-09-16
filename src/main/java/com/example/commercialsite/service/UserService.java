package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import com.example.commercialsite.dto.response.getAllUsersToAdmin;
import com.example.commercialsite.entity.Users;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponseEntity<String> registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException, UnsupportedEncodingException;
    ResponseEntity<?>  verifyCustomer(String code);
    Optional<Users> getUserById(Long userId);
    ResponseEntity<String> holdUser(Long userId);
    ResponseEntity<String>removeHoldFromUser(Long userId);
    ResponseEntity<List<getAllUsersToAdmin>> getAllUsers();



    Users getCustomer(String email);
}
