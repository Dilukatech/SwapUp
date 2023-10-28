package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.dto.request.PaymentRequestDto;
import com.example.commercialsite.service.FavoriteService;
import com.example.commercialsite.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping(path = "/save-payment")
    public ResponseEntity<String> savePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        boolean isPaymentSaved = paymentService.savePayment(paymentRequestDto);
        if (isPaymentSaved) {
            return ResponseEntity.ok("Payment saved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the payment.");
        }
    }
}
