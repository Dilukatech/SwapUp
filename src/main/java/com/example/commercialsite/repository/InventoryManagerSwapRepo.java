package com.example.commercialsite.repository;

import com.example.commercialsite.entity.InventoryManagerSwap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface InventoryManagerSwapRepo extends JpaRepository<InventoryManagerSwap, Long> {

    boolean existsBySwapIdEquals(Long swapId);

    InventoryManagerSwap getReferenceBySwapIdEquals(Long swapId);

    List<InventoryManagerSwap> getAllByCustomerIdEquals(long id);
}
