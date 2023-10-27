package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.HelpDto;
import com.example.commercialsite.dto.request.RequestTokenRequestDto;
import com.example.commercialsite.dto.response.UsersDTO;
import com.example.commercialsite.service.CustomerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName()); // logger is the recommended way to handel exceptions

    @Autowired
    private CustomerService customerService;


    //@PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/RequestTokenFromCustomer")
    public ResponseEntity<StandardResponse> RequestTokenFromCustomer(@RequestBody RequestTokenRequestDto requestTokenRequestDto) {
        logger.info("Logging begins... RequestTokenFromCustomer");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
            result = customerService.CreateRequestToken(requestTokenRequestDto);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<>(
                    new StandardResponse(500,"internal server error in Request Token From Customer",null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        logger.info("Done... RequestTokenFromCustomer");
        return result;
    }

    @PostMapping("/AllRequestToken")
    public ResponseEntity<StandardResponse> AllRequestTokenFromCustomer(@RequestBody UsersDTO usersDTO) {
        logger.info("Logging begins... AllRequestTokenFromCustomer");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
            result = customerService.AllRequestTokenFromCustomer(usersDTO);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<>(
                    new StandardResponse(500,"internal server error in getting All Request Token From Customer",null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        logger.info("Done... AllRequestTokenFromCustomer");
        return result;
    }

    @PostMapping("/help-request")
    public ResponseEntity<StandardResponse> HelpRequestFromCustomer(@RequestBody HelpDto helpDto){
        logger.info("Logging begins... HelpRequestFromCustomer");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try{
            result = customerService.HelpRequestFromCustomer(helpDto);
        }catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<>(
                    new StandardResponse(500,"internal server error in getting All Request Token From Customer",null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        logger.info("Done... HelpRequestFromCustomer");
        return result;
    }
}
