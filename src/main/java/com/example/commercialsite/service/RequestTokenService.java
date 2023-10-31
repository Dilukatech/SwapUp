package com.example.commercialsite.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RequestTokenService {

    long countRequestTokens();
    ResponseEntity<Long> getCountOfRequestTokens();

    long countRequestTokensWithStatusOne();
    ResponseEntity<Long> getCountOfRequestTokensWithStatusOne();
}
