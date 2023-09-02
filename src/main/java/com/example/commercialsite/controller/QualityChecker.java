package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.service.QualityCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qchecker")
public class QualityChecker {
    @Autowired
    private QualityCheckerService qualityCheckerService;


    @PostMapping("/accept-request-token")
    public ResponseEntity<String> acceptRequestToken(@RequestBody AcceptRequestDto acceptRequestDto) {
        try {
            return qualityCheckerService.AcceptRequestToken(acceptRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while processing the Accept Request Token."+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/reject-request-token")
    public ResponseEntity<String> rejectRequestToken(@RequestBody RejectRequestDto rejectRequestDto) {
        try {
            return qualityCheckerService.rejectRequestToken(rejectRequestDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while processing the Reject Request Token." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
