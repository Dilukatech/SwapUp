package com.example.commercialsite.controller;

//import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.CustomerRequestTokenDto;
import com.example.commercialsite.dto.response.CustomerRequestTokenResponseDto;
import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.utill.StandardResponse;
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
    public ResponseEntity<StandardResponse> RequestTokenFromCustomer(@RequestBody CustomerRequestTokenDto customerRequestTokenDto) {
        try {
            return customerService.CreateRequestToken(customerRequestTokenDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"internal server error in Request Token From Customer",null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
