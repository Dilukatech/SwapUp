package com.example.commercialsite.repository;

import com.example.commercialsite.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TokenRepo extends JpaRepository<Token, Long> {

    List<Token> getAllByUserIdAndStateEquals(long id, boolean state);
}
