package com.example.commercialsite.controller;

//import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.CustomerRequestTokenDto;
import com.example.commercialsite.dto.response.CustomerRequestTokenResponseDto;
import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;


    @PostMapping("/RequestTokenFromCustomer")
    public ResponseEntity<CustomerRequestTokenResponseDto> RequestTokenFromCustomer(@RequestBody CustomerRequestTokenDto customerRequestTokenDto) {
        try {
            CustomerRequestTokenResponseDto dto = customerService.CreateRequestToken(customerRequestTokenDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CustomerRequestTokenResponseDto(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
