package com.example.commercialsite.dto.response;

import com.example.commercialsite.entity.RequestToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestTokenResponse {
    private List<RequestTokenResponseDto> data;
    private String message;
}
