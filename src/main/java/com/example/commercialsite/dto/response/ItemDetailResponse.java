package com.example.commercialsite.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDetailResponse {

    private String telephone;
    private String message;
    private String address;
}
