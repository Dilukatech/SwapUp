package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "Token")
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false, updatable = false)
    private Long tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "state")
    private boolean state;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "price")
    private int price;

    //@ManyToOne(mappedBy = "requestToken", cascade = CascadeType.ALL)
    //private Item item;

    @OneToOne(mappedBy = "token", cascade = CascadeType.ALL)
    private RequestToken requestToken;

}
