package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTableResponseDTO {

    private String planName;
    private LocalDateTime createdDate;
    private boolean isPayment;
}
