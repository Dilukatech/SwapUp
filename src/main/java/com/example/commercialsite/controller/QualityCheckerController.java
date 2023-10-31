package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.service.QualityCheckerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/quality-checker")
public class QualityCheckerController {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName()); // logger is the recommended way to handel exceptions


    @Autowired
    private QualityCheckerService qualityCheckerService;

    @PostMapping("/image-Checking")//accept or reject item by checking image
    public ResponseEntity<StandardResponse> imageChecking(@RequestParam("request-id") Long requestId,
                                                          @RequestParam("quality-checker-id") long qualityCheckerId,
                                                          @RequestParam("image-status") int imageStatus) {

        ResponseEntity<StandardResponse> result;

        try {
            result = qualityCheckerService.imageChecking(requestId,qualityCheckerId,imageStatus);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"internal server error while processing the Accept Request Token."+ ex.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("/accept-request-token")//accept item by physically checking
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


    @PostMapping("/reject-request-token")//reject item by physically checking
    public ResponseEntity<StandardResponse> rejectRequestToken(@RequestBody RejectRequestDto rejectRequestDto) {
        logger.info("Logging begins... rejectRequestToken");   // log INFO-level message
        ResponseEntity<StandardResponse> result;

        try {
            result = qualityCheckerService.rejectRequestToken(rejectRequestDto);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing the reject Request Token: " + ex.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

        logger.info("Done... rejectRequestToken");
        return result;
    }

    @GetMapping("/get-all-request-token")//get all request token to view quality checker
    public ResponseEntity<StandardResponse>getAllRequestToken(){
            try {
                return qualityCheckerService.getAllRequestToken();

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<StandardResponse>(
                        new StandardResponse(500,"Error while processing get all request token by inventory manager: " + e.getMessage(),null),
                        HttpStatus.INTERNAL_SERVER_ERROR);

            }

    }

    @GetMapping("/get-shipping-approved-requet-token")//get shipping approved request token to view quality checker
    public ResponseEntity<StandardResponse>getShippingApprovedRequestToken(){
        try {
            return qualityCheckerService.getShippingApprovedRequestToken();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing get shipping approved request token by inventory manager: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
