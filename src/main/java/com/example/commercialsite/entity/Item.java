package com.example.commercialsite.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@AllArgsConstructor
@NoArgsConstructor
//@Data     // gives a circular reference error // moving toString annotation
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "Item") // hibernate annotations
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "price")
    private int price;

    @Column(name = "available_status")
    private boolean availableStatus;

    @Column(name = "size", nullable = false)
    private String size;

    @ToString.Exclude // to fix circular reference problem
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private RequestToken requestToken;


}
