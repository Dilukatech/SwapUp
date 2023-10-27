package com.example.commercialsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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
    private boolean activeState;
    private String size;
}
