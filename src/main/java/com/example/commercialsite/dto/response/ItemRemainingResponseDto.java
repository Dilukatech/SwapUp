package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRemainingResponseDto {
    private Long itemId;
    private String color;
    private String imageURL;
    private String gender;
    private String type;
    private int price;
    private boolean availableStatus;
    private String size;
    //private RequestToken requestToken;
}
