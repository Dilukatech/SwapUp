package com.example.commercialsite.service;

import com.example.commercialsite.dto.request.HoldDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<String> holdAccount(HoldDto holdDto);
}
