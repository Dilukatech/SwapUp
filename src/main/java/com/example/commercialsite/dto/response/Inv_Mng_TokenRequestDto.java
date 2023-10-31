package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inv_Mng_TokenRequestDto {
    private Long inventoryManagerTokenRequestId;
    private Long inventoryManagerId;
    private Long requestTokenId;
    private LocalDateTime shippedOrArrivedTime;
    private int shipmentStatus;
}
