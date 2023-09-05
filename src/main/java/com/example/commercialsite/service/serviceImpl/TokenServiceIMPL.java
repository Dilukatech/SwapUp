package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Token;
import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import static com.example.commercialsite.modules.GenerateToken.getMd5;

@Service
public class TokenServiceIMPL implements TokenService {
    @Autowired
    private RequestTokenRepo requestTokenRepo;

    public Token GenerateToken (Long requestTokenId, int price) throws NoSuchAlgorithmException {
        //requesttoken object
        RequestToken requestToken = requestTokenRepo.getReferenceById(requestTokenId);
        //new token object
        Token token = new Token();
        token.setToken(getMd5(requestToken.toString()));
        token.setState(true);
        token.setTimeCreated(LocalDateTime.now());
        token.setPrice(price);

        return token;
    }
}
