package com.example.commercialsite.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllRequestResponseDto {
    private Long requestTokenId;
   // private Long qualityCheckerId;
    private int status;
}
