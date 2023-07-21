package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "favorite_list")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Favorite {



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id") // This maps to the primary key column "user_id" in the "favorite_list" table
//    private Long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "created_date")
    private Date createDate;

    @PrePersist
    private void prePersist() {
        createDate = new Date(); // Set the current date before persisting the entity
    }

    @PreUpdate
    private void preUpdate() {
        createDate = new Date(); // Update the created_date to the current date before updating the entity
    }


}
