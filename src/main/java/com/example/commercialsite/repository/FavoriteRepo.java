package com.example.commercialsite.repository;

import com.example.commercialsite.entity.Favorite;
import com.example.commercialsite.entity.Item;
import com.example.commercialsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);

    Favorite findByUserAndItem(User user, Item item);
}
