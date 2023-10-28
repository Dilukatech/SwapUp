package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Transactional
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

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "price")
    private int price;

    @Column(name = "available_status")
    private boolean availableStatus;

    @Column(name = "size", nullable = false)
    private String size;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private RequestToken requestToken;

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", color='" + color + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", availableStatus=" + availableStatus +
                ", size='" + size + '\'' +
                '}';
    }



}
