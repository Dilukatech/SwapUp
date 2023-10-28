package com.example.commercialsite.controller;


import com.example.commercialsite.dto.request.CreateCheckoutSessionRequest;
import com.example.commercialsite.dto.response.SubscribedResponse;
import com.example.commercialsite.entity.PaymentTable;
import com.example.commercialsite.repository.PaymentTableRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class CheckoutController {

    @Value("${stripe.secret.key}")
    private String stripeApiKey;

    @Autowired
    PaymentTableRepository paymentTableRepository;

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestBody CreateCheckoutSessionRequest request) {

        PaymentTable paymentTable=new PaymentTable();
        paymentTable.setUserId(request.getUserId());
        paymentTable.setPrice(request.getPrice());
        paymentTable.setPlanName(request.getPlanName());
        paymentTable.setPayment(false);
        paymentTable.setExpiredDate(null);
        LocalDateTime currentDateTime = LocalDateTime.now();
        paymentTable.setCreatedDate(currentDateTime);

        paymentTableRepository.save(paymentTable);



        Stripe.apiKey = stripeApiKey;

        String YOUR_DOMAIN = "http://localhost:3000"; // Update to your frontend's URL
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/success")
                        .setCancelUrl(YOUR_DOMAIN + "/cancel")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPrice(request.getPriceId()) // Replace with the actual Price ID
                                        .build())
                        .build();
        Session session = null;
        try {
            session = Session.create(params);
            System.out.println("Checkout session created successfully.");
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return session.getUrl();
    }


    @PutMapping("/payment-complete/{userId}")
    public ResponseEntity<?> updateCompletedPayment(@PathVariable Long userId){
        Optional <List<PaymentTable>> optionalPaymentTableList=paymentTableRepository.findAllByUserId(userId);
        if(optionalPaymentTableList.isEmpty()){
            return new ResponseEntity<>("Empty",HttpStatus.OK);
        }
        List<PaymentTable> paymentTableList=optionalPaymentTableList.get();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Optional<PaymentTable> closestPayment = paymentTableList.stream()
                .min(Comparator.comparing(payment -> Math.abs(ChronoUnit.SECONDS.between(payment.getCreatedDate(), currentDateTime))));

        if (closestPayment.isPresent()) {
            PaymentTable closestPaymentTable = closestPayment.get();

            if(!closestPaymentTable.isPayment()){
                LocalDateTime createdDate = closestPaymentTable.getCreatedDate();
                LocalDateTime expiredDate = createdDate.plusMonths(3);
                closestPaymentTable.setExpiredDate(expiredDate);
                closestPaymentTable.setPayment(true);

                paymentTableRepository.save(closestPaymentTable);
            }

            return new ResponseEntity<>("Payment completed for user with ID " + userId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No suitable payment found", HttpStatus.OK);
        }
    }

    @GetMapping("/get-subscribed-user/{userId}")
    public ResponseEntity<?> getSubscribedUser(@PathVariable Long userId){
        SubscribedResponse response=new SubscribedResponse();
        Optional<List<PaymentTable>> optionalPaymentTableList = paymentTableRepository.findAllByUserId(userId);
        if(optionalPaymentTableList.isEmpty()){
            response.setSubscribed(false);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        List<PaymentTable> paymentTableList=optionalPaymentTableList.get();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Optional<PaymentTable> closestPayment = paymentTableList.stream()
                .min(Comparator.comparing(payment -> Math.abs(ChronoUnit.SECONDS.between(payment.getCreatedDate(), currentDateTime))));

        if (closestPayment.isPresent() && closestPayment.get().getExpiredDate()!=null) {
            PaymentTable closestPaymentTable = closestPayment.get();
            LocalDateTime expiredDate = closestPaymentTable.getExpiredDate();

            if (currentDateTime.isAfter(expiredDate)) {
                // The current date and time is after the expiredDate
                // You can perform actions accordingly here.
                response.setSubscribed(false);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }else{
                response.setSubscribed(true);
                response.setPlanName(closestPaymentTable.getPlanName());
                response.setCreatedDate(closestPaymentTable.getCreatedDate());
                response.setExpiredDate(closestPaymentTable.getExpiredDate());
                return new ResponseEntity<>(response,HttpStatus.OK);
            }



        } else {
            response.setSubscribed(false);
             return new ResponseEntity<>(response,HttpStatus.OK);
        }


    }



}

