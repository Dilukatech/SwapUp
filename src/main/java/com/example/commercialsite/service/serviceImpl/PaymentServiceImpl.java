package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.request.PaymentRequestDto;
import com.example.commercialsite.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean savePayment(PaymentRequestDto paymentRequestDto) {
        return false;
    }
}
