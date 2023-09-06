package com.example.commercialsite.repository;

import com.example.commercialsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmailEquals(String email);
    User findByVerificationCodeEquals(String code);
    User findByEmailEquals(String email);

}
