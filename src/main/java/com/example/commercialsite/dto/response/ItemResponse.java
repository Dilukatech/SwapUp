package com.example.commercialsite.dto.response;

import com.example.commercialsite.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private List<ItemDTO> data;
    private String message;
}
