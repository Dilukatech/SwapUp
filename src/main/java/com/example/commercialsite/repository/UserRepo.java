package com.example.commercialsite.repository;

import com.example.commercialsite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmailEquals(String email);
    Users findByVerificationCodeEquals(String code);
    Users findByEmailEquals(String email);

}
