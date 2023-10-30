package com.example.commercialsite.dto;

import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
