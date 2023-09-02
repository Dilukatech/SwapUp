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

    @Column(name = "quality_status")
    private String qualityStatus;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "active_status")
    private boolean activeState;

    @Column(name = "size", nullable = false)
    private String size;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequestTokenId", referencedColumnName = "requestTokenId")
    private RequestToken requestToken;


    public Item(String color, String imageURL, String gender, String type, String priceRange, boolean activeState, String size, RequestToken requestToken) {
        this.color = color;
        this.imageURL = imageURL;
        this.gender = gender;
        this.type = type;
        this.priceRange = priceRange;
        this.activeState = activeState;
        this.size = size;
        this.requestToken = requestToken;
    }
}
