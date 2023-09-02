package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequestTokenResponseDto {
    private Long requestTokenId;
    private Long customerId;
    private int status;
    private String itemDescription;
    private String itemImage;
    private LocalDateTime requestDateTime;
}
