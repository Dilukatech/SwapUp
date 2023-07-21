package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping(path = "/save-item")
    public ResponseEntity<String> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        boolean isItemSaved = itemService.saveItem(itemSaveRequestDTO);
        if (isItemSaved) {
            return ResponseEntity.ok("Item saved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the item.");
        }
    }
}
