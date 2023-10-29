package com.example.commercialsite.service;

import com.example.commercialsite.dto.response.PaymentTableResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<PaymentTableResponseDTO> getAllPaymentsByIsPayment(boolean isPayment);
}
