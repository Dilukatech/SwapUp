package com.example.commercialsite.dto;

import com.example.commercialsite.entity.RequestToken;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemDTO {

    private Long itemId;
    private String color;
    private String imageURL;
    private String gender;
    private String type;
    private int price;
    private boolean availableStatus;
    private String size;
    private RequestToken requestToken;
}
