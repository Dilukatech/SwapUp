package com.example.commercialsite.service;

import com.example.commercialsite.entity.Token;

import java.security.NoSuchAlgorithmException;

public interface TokenService {
    //ResponseEntity<StandardResponse> GenerateToken (RequestToken requestToken);
    Token GenerateToken (Long requestTokenId, int price) throws NoSuchAlgorithmException; // take a requesttoken object and return a token object
}
