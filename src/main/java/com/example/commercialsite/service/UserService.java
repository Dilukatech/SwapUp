package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService {

    String registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException, UnsupportedEncodingException;
    ResponseEntity<?> verifyCustomer(String code);
}
