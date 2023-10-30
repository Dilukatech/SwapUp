package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.PaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    boolean savePayment(PaymentRequestDto paymentRequestDto);
}
