package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcceptRequestDto {
    // object id
    private Long requestTokenId;
    // quality checker id
    private Long qualityCheckerId;
    // updated by quality-checker
    // private boolean activeState; // no need for status tobe passed // it becomes true upon accepting the request
    // item details
    private String color;
    private String imageURL;
    private String gender;
    private String type;
    private int price;
    private String size;
    private String description;

}
