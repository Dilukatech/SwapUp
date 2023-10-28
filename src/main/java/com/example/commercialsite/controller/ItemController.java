package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.dto.response.ItemResponse;
import com.example.commercialsite.entity.Item;
//import com.example.commercialsite.service.ItemService;
import com.example.commercialsite.services.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

//    @Autowired
//    private ItemService itemService;

    @Autowired
    CustomService customService;


//    @PostMapping(path = "/save-item")
//    public ResponseEntity<String> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//        boolean isItemSaved = itemService.saveItem(itemSaveRequestDTO);
//        if (isItemSaved) {
//            return ResponseEntity.ok("Item saved successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the item.");
//        }
//    }


    @GetMapping("/get-items")
    public ResponseEntity<?> getItems() {
        ItemResponse response = customService.getAllItems();
        if(Objects.equals(response.getMessage(), "Fetch successfully")){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(Objects.equals(response.getMessage(), "No data found")){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMessage("Server error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
