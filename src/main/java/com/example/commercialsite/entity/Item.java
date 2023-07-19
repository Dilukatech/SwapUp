package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "image_url",nullable = false)
    private String imageURL;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "quality_status",nullable = false)
    private String qualityStatus;

    @Column(name = "size",nullable = false)
    private String size;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "active_status")
    private boolean activeState;

}
