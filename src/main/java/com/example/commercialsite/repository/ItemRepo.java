package com.example.commercialsite.repository;

import com.example.commercialsite.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
