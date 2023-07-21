package com.example.commercialsite.service;

import com.example.commercialsite.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteService {

    void deleteFromFavorites(Long userId, Long itemId);

    List<ItemDTO> getFavoriteItemByUserId(Long userId);

    void addToFavorites(Long userId, Long itemId);
}
