package com.example.commercialsite.repository;

import com.example.commercialsite.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UsersRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmailEquals(String email);
    Users findByVerificationCodeEquals(String code);

    Users findByUserId(Long customerId);

    boolean existsByUserIdEquals(Long customerId);

    //Users findByEmailEquals(String email);
    // trying to fuse Usermapper with the UserRepo
    //List<UsersDTO> entityListToUsersDTODtoList(List<Users> users); // previous method // findAll() might give the supposed result

}
