package com.example.commercialsite.controller;

import com.example.commercialsite.dto.request.ItemSaveRequestDTO;
import com.example.commercialsite.service.ItemService;
import com.example.commercialsite.utill.StandardResponse;
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

    @PostMapping(path = "/save_item")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        itemService.saveItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Item Saved Successfull", itemSaveRequestDTO),
                HttpStatus.OK
        );
    }
    // djsfslfjsldijfosijfsoisoifjs
}
