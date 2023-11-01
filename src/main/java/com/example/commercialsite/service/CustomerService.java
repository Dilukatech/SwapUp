package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.HelpDto;
import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.request.SwapRequestDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {
//    CustomerRequestTokenResponseDto CreateRequestToken(CustomerRequestTokenDto customerRequestTokenDto);
    ResponseEntity<StandardResponse> CreateRequestToken(RequestTokenRequestDto requestTokenRequestDto);

    ResponseEntity<StandardResponse> AllRequestTokenFromCustomer(@RequestBody UsersDTO usersDTO);


    ResponseEntity<StandardResponse> HelpRequestFromCustomer(HelpDto helpDto);

    ResponseEntity<StandardResponse> requestSwap(SwapRequestDto swapRequestDto);
}
