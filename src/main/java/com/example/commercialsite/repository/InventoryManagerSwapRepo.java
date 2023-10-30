package com.example.commercialsite.repository;

import com.example.commercialsite.entity.InventoryManagerSwap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryManagerSwapRepo extends JpaRepository<InventoryManagerSwap,Long> {
    boolean existsBySwapIdEquals(Long swapId);

    InventoryManagerSwap getReferenceBySwapIdEquals(Long swapId);
}
