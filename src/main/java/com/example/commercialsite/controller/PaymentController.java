package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.ConfirmPaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestParam Long amount) throws StripeException {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount(amount)
                .setCurrency("usd")
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);

        return intent.getClientSecret();
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<String> confirmPayment(@RequestBody ConfirmPaymentRequest request) {
        try {
            PaymentIntent intent = PaymentIntent.retrieve(request.getPaymentIntentId());
            intent.confirm();
            return new ResponseEntity<>("Payment confirmed!", HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>("Payment confirmation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
