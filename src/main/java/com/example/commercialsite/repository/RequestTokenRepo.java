package com.example.commercialsite.repository;

import com.example.commercialsite.entity.RequestToken;
import com.example.commercialsite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RequestTokenRepo extends JpaRepository<RequestToken, Long> {
    RequestToken getRequestTokenByRequestTokenId(Long requestTokenId);

    List<RequestToken> getAllByCustomerId(Users customerId);

    List<RequestToken> getAllByShippingApprovalEquals(int i);


    long countByStatus(int i);

    long countByStatusAndShippingApproval(int status, int shippingApproval);
}