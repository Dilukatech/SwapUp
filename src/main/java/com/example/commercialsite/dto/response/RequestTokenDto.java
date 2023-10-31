package com.example.commercialsite.dto.response;

import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.Token;
import com.example.commercialsite.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder




public class RequestTokenDto {


    private Long requestTokenId;
    private Users customerId;
    private Users qualityCheckerId;
    private int status;
    private int shippingApproval;
    private String itemDescription;
    private String itemImage;
    private LocalDateTime requestDateTime;
    private Item item;
    private Token token;
}
