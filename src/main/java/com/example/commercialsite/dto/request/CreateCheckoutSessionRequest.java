package com.example.commercialsite.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCheckoutSessionRequest {
    private String priceId;
    private String planName;
    private Long userId;
    private double price;
}
