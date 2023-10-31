package com.example.commercialsite.service;

import com.example.commercialsite.dto.response.InventoryManagerTokenShippingRequestDTO;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryManagerService{

    ResponseEntity<StandardResponse> arrivedOrReturnItem(Long inventoryManagerId, Long requestId,int shippingStatus);


    InventoryManagerTokenShippingRequestDTO countShipmentStatus(int shipmentStatus);
}
