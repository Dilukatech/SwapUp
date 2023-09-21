package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity(name = "Item")
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

//    @Column(name = "quality_status")
//    private String qualityStatus;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "price")
    private int price;

    @Column(name = "available_status")
    private boolean availableStatus;

    @Column(name = "size", nullable = false)
    private String size;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequestTokenId", referencedColumnName = "requestTokenId")
    private RequestToken requestToken;

}
