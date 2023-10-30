package com.example.commercialsite.service;

import com.example.commercialsite.dto.response.ItemRemainingResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<ItemRemainingResponseDto> getAllRemainingItemByAvailableStatus(boolean availableStatus);
}
