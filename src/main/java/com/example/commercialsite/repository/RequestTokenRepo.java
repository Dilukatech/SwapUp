package com.example.commercialsite.repository;

import com.example.commercialsite.entity.RequestToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RequestTokenRepo extends JpaRepository<RequestToken,Long> {
    RequestToken getRequestTokenByRequestTokenId(Long requestTokenId);
}
