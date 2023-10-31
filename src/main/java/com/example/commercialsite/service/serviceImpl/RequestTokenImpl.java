package com.example.commercialsite.service.serviceImpl;

import com.example.commercialsite.repository.RequestTokenRepo;
import com.example.commercialsite.service.RequestTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestTokenImpl implements RequestTokenService {

   @Autowired
   private RequestTokenRepo requestTokenRepo;

    @Override
    public long countRequestTokens() {
        return requestTokenRepo.count();
    }

    @Override
    public ResponseEntity<Long> getCountOfRequestTokens() {
        long count = countRequestTokens();
        return ResponseEntity.ok(count);
    }

    @Override
    public long countRequestTokensWithStatusOne() {
        return requestTokenRepo.countByStatus(1);
    }

    @Override
    public ResponseEntity<Long> getCountOfRequestTokensWithStatusOne() {
        long count = countRequestTokensWithStatusOne();
        return ResponseEntity.ok(count);
    }

    @Override
    public long countRequestTokensWithStatusAndShippingApproval(int status, int shippingApproval) {
        return requestTokenRepo.countByStatusAndShippingApproval(status, shippingApproval);
    }

    @Override
    public ResponseEntity<Long> getTotalCountOfRequestTokensWithStatusAndShippingApproval() {
        long count = countRequestTokensWithStatusAndShippingApproval(-1, -1);
        return ResponseEntity.ok(count);
    }
}
