package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.service.QualityCheckerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/quality-checker")
public class QualityCheckerController {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName()); // logger is the recommended way to handel exceptions


    @Autowired
    private QualityCheckerService qualityCheckerService;


    @PostMapping("/accept-request-token")
    public ResponseEntity<StandardResponse> acceptRequestToken(@RequestBody AcceptRequestDto acceptRequestDto) {
        logger.info("Logging begins... acceptRequestToken");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
            result = qualityCheckerService.AcceptRequestToken(acceptRequestDto);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"internal server error while processing the Accept Request Token."+ ex.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Done... acceptRequestToken");
        return result;
    }


    @PostMapping("/reject-request-token")
    public ResponseEntity<StandardResponse> rejectRequestToken(@RequestBody RejectRequestDto rejectRequestDto) {
        logger.info("Logging begins... rejectRequestToken");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
            result = qualityCheckerService.rejectRequestToken(rejectRequestDto);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing the Accept Request Token: " + ex.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

        logger.info("Done... rejectRequestToken");
        return result;
    }



}
