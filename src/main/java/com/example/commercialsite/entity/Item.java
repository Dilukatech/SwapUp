package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Item")
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

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "active_status")
    private boolean activeState;

    @Column(name = "size", nullable = false)
    private String size;

}
