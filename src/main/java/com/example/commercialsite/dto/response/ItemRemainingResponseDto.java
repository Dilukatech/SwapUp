package com.example.commercialsite.dto.response;

import com.example.commercialsite.entity.RequestToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
