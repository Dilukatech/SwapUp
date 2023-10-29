package com.example.commercialsite.repository;

import com.example.commercialsite.entity.PaymentTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public  interface  PaymentTableRepository extends JpaRepository<PaymentTable,Long> {

    Optional<List<PaymentTable>> findAllByUserId(Long userId);




}
