package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.HoldDto;
import com.example.commercialsite.dto.request.UserRegisterRequestDTO;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.entity.Users;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponseEntity<String> registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException;
    ResponseEntity<?>  verifyCustomer(String code);
    Optional<Users> getUserById(Long userId);
    ResponseEntity<List<UsersDTO>> getAllUsers(); // return a list of users objects


}
