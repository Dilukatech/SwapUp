package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldDto {      // hold & remove user account from admin
    private Long adminId;
    private Long customerId;
    private Boolean action;
    private String reason;
}
