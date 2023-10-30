package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.RejectRequestDto;
import com.example.commercialsite.service.InventoryManagerService;
import com.example.commercialsite.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory-manager")
public class InventoryManager {
    @Autowired
    private InventoryManagerService inventoryManagerService;

    @PostMapping("/arrived_or_return_item")//arrived request item to the shop or shipped rejected item to customer
    public ResponseEntity<StandardResponse> arrivedOrReturnItem(@RequestParam("inventory-manager-id") Long inventoryManagerId,
                                                            @RequestParam("request-token-id") Long requestId,
                                                            @RequestParam("shipping-status") int shippingStatus //1-> item received successfully and -1->reject item shipped successfully
                                                            ) {
        try {
            return inventoryManagerService.arrivedOrReturnItem(inventoryManagerId,requestId,shippingStatus);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing the arrived shipment of inventory manager: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
