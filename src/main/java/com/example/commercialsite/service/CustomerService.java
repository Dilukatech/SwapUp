package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.CustomerRequestTokenDto;
import com.example.commercialsite.dto.response.CustomerRequestTokenResponseDto;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
//    CustomerRequestTokenResponseDto CreateRequestToken(CustomerRequestTokenDto customerRequestTokenDto);
    ResponseEntity<StandardResponse> CreateRequestToken(CustomerRequestTokenDto customerRequestTokenDto);
}
