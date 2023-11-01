package com.example.commercialsite.repository;

import com.example.commercialsite.entity.PaymentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PaymentTableRepository extends JpaRepository<PaymentTable,Long> {

    Optional<List<PaymentTable>> findAllByUserId(Long userId);

    //List<PaymentTable> findByIsPayment(boolean isPayment);

}
