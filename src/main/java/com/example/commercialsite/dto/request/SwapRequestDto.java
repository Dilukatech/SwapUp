package com.example.commercialsite.dto.request;

import com.example.commercialsite.dto.response.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SwapRequestDto extends UsersDTO {

    private long customerId;
    private long itemId;
    private long tokenId;


}
