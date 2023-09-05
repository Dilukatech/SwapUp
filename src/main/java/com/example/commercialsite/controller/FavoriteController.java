package com.example.commercialsite.controller;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.dto.request.AddFavoritesRequestDTO;
import com.example.commercialsite.exception.ItemNotFoundException;
import com.example.commercialsite.exception.UserNotFoundException;
import com.example.commercialsite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@CrossOrigin
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;



    @PostMapping("/add-favorites")
    public ResponseEntity<String> addToFavorites(@RequestBody AddFavoritesRequestDTO addFavoritesRequestDTO){
        try{
            favoriteService.addToFavorites(addFavoritesRequestDTO.getUserId(),addFavoritesRequestDTO.getItemId());
            return new ResponseEntity<>("Item added to favorites successfuly", HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("Failed to add item to favorites:" +e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<String> deleteFromFavorites(@PathVariable Long userId, @PathVariable Long itemId) {
        try {
            favoriteService.deleteFromFavorites(userId, itemId);
            return ResponseEntity.ok("Item deleted from favorites successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing item from favorites.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ItemDTO>> getFavoriteItemsByUserId(@PathVariable Long userId){
        List<ItemDTO>  favoriteItems = favoriteService.getFavoriteItemByUserId(userId);
        return ResponseEntity.ok(favoriteItems);
    }
}
