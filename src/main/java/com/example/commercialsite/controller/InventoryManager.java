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

    @GetMapping("/get-all-unprocessed-swap-item")//get all unprocessed item to view inventory manager
    public ResponseEntity<StandardResponse> GetAllUnprocessedSwapItem(){
        try {
            return inventoryManagerService.getAllUnprocessedSwapItems();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing get all unprocessed item by inventory manager: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/get-all-unShipped-swap-item")
    public ResponseEntity<StandardResponse> GetAllUnshippedSwapItems(){
        try {
            return inventoryManagerService.getAllUnshippedSwapItems();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing get all unshipped swap item by inventory manager: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

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

    @PostMapping("/shipped-swapping-item")//selected swap item shipped to the customer
    public ResponseEntity<StandardResponse> shippedSwappingItem(@RequestParam("inventory-manager-id") Long inventoryManagerId,
                                                                @RequestParam("swap-id") Long swapId,
                                                                @RequestParam("status") boolean status //0->unprocessed, 1->shipped
    ) {
        try {
            return inventoryManagerService.shippedSwappingItem(inventoryManagerId,swapId,status);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(500,"Error while processing shipping swapped item: " + e.getMessage(),null),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
