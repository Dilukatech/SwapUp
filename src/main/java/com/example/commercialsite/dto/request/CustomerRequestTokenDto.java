package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequestTokenDto {
    private Long customerId;
    private String itemDescription;
    private String itemImage;

}
