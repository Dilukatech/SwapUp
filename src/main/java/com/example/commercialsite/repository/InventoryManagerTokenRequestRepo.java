package com.example.commercialsite.repository;

import com.example.commercialsite.entity.InventoryManagerTokenRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface InventoryManagerTokenRequestRepo extends JpaRepository<InventoryManagerTokenRequest,Long> {

    boolean existsByRequestTokenId(Long requestId);

    InventoryManagerTokenRequest getReferenceByRequestTokenIdEquals(Long requestId);
    List<InventoryManagerTokenRequest> findAllByShipmentStatusEquals(int i);
}
