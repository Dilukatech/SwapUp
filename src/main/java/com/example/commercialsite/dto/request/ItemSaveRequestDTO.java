package com.example.commercialsite.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSaveRequestDTO {

    private String color;
    private String imageURL;
    private String gender;
    private String qualityStatus;
    private String size;
    private String profilePicture;
    private boolean activeState;

}
