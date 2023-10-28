package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.AcceptRequestDto;
import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.dto.response.RequestTokenResponse;
import com.example.commercialsite.service.QualityCheckerService;
import com.example.commercialsite.services.CustomService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/quality-checker")
public class QualityCheckerController {
    @Autowired
    private QualityCheckerService qualityCheckerService;

    @Autowired
    CustomService customService;
    @PostMapping("/accept-request-token")
    public ResponseEntity<StandardResponse> acceptRequestToken(@RequestBody AcceptRequestDto acceptRequestDto) {
        try {
            return qualityCheckerService.AcceptRequestToken(acceptRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"internal server error while processing the Accept Request Token."+ e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/reject-request-token")
    public ResponseEntity<StandardResponse> rejectRequestToken(@RequestBody RejectRequestDto rejectRequestDto) {
        try {
            return qualityCheckerService.rejectRequestToken(rejectRequestDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing the Accept Request Token: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//    @GetMapping("/get-request-token")
//    public ResponseEntity<?> getAllRequestToken(){
//        RequestTokenResponse response=customService.getAllRequestToken();
//        if(Objects.equals(response.getMessage(), "Fetch successfully")){
//            return new ResponseEntity<>(response,HttpStatus.OK);
//        }
//        response.setMessage("Server error");
//        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping("/get-request-token")
    public ResponseEntity<?> getAllRequestToken() {
        RequestTokenResponse response = customService.getAllRequestToken();
        if ("Fetch successfully".equals(response.getMessage())) {
            return ResponseEntity.ok(response); // Return a 200 OK response
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
