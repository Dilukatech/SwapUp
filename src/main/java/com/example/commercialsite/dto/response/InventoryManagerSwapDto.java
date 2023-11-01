package com.example.commercialsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryManagerSwapDto {
    private Long swapId;
    private Long inventoryManagerId;
    private LocalDateTime timeCreated;
    private boolean swappingStatus;

}
