package com.example.commercialsite.dto.request;

import com.example.commercialsite.entity.RequestToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcceptRequestDto {

    private String color;
    private String imageURL;
    private String gender;
    private String type;
    private String priceRange;
    private boolean activeState;
    private String size;
    private Long requestTokenId;
    private Long qualityCheckerId;

}
