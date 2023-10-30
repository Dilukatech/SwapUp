package com.example.commercialsite.service;

import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface InventoryManagerService{

    ResponseEntity<StandardResponse> arrivedOrReturnItem(Long inventoryManagerId, Long requestId,int shippingStatus);
}
