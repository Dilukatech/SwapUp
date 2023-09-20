package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.dto.ItemDTO;
import com.example.commercialsite.entity.Favorite;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.Users;
import com.example.commercialsite.repository.FavoriteRepo;
import com.example.commercialsite.repository.ItemRepo;
import com.example.commercialsite.repository.UserRepo;
import com.example.commercialsite.service.FavoriteService;
import com.example.commercialsite.service.UserService;
import com.example.commercialsite.utill.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepo favoriteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserService userService;

    @Override
    public void addToFavorites(Long userId, Long itemId) {
        Users users = userRepo.findById(userId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);

        if (users == null) {
            throw new RuntimeException("User not found");
        }

        if (item == null) {
            throw new RuntimeException("Item not found");
        }

        // Check if the item is already in the customer's favorites before adding
        if(!isItemInFavorites(users,item)){
            Favorite favorite = new Favorite();
            favorite.setUsers(users);
            favorite.setItem(item);
            favoriteRepo.save(favorite);
        }
    }

    private boolean isItemInFavorites(Users users, Item item) {

        // Get the list of favorites for the customer from the database.
        List<Favorite> favorites = favoriteRepo.findByUsers(users);

        // Check if the item is in the list of favorites.
        for(Favorite favorite : favorites){
            if(favorite.getItem().equals(item)){
                return true;
            }
        }

        // If the loop doesn't find a matching item, return false.
        return false;
    }

    @Override
    public void deleteFromFavorites(Long userId, Long itemId) {

        Users users = userRepo.findById(userId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);

        if (users == null) {
            throw new RuntimeException("User not found");
        }

        if (item == null) {
            throw new RuntimeException("Item not found");
        }

        // Find the favorite entry for the customer and item combination
        Favorite favorite = favoriteRepo.findByUsersAndItem(users,item);
        if(favorite != null){
            favoriteRepo.delete(favorite);
        }
    }

    @Override
    public List<ItemDTO> getFavoriteItemByUserId(Long userId) {
        Optional<Users> userOptional = userService.getUserById(userId);
        if(userOptional.isEmpty()) {
            // Handle case when customer is not found
            return Collections.emptyList();
        }

        Users users = userOptional.get();
        List<Favorite> favorites = favoriteRepo.findByUsers(users);
        List<Item> favoriteItems = favorites.stream().map(Favorite::getItem).collect(Collectors.toList());

        // Convert List<Item> to List<ItemDTO> using the ItemMapper
        return itemMapper.mapItemsToItemDTOs(favoriteItems);
    }
}
