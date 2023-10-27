package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestTokenResponseDto {
    private Long requestTokenId;
    private Long customerId;
    private Long itemId;
    private int status;
    private String itemDescription;
    private String itemImage;
    private LocalDateTime requestDateTime;
}
