package com.example.commercialsite.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
//@Data     // gives a circular reference error // moving toString annotation
@ToString
@Getter
@Setter
@Transactional
@Entity(name = "RequestToken")
@Table(name = "request_token")
public class RequestToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_token_id")
    private Long requestTokenId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users customerId;

    @ManyToOne
    @JoinColumn(name = "quality_checker_id")
    private Users qualityCheckerId;

    @Column(name = "status")
    private int status;

    @Column(name = "item_Description")
    private String itemDescription;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "request_date_time")
    private LocalDateTime requestDateTime;

    // data jpa relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @ToString.Exclude // to fix circular reference problem
    private Item item;

    // data jpa relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    @ToString.Exclude // to fix circular reference problem
    private Token token;



}
