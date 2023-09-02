package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RequestTokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestTokenId;

    private Long customerId;
    private Long qualityCheckerId;

    private int status;
    private String itemDescription;
    private String itemImage;
    private LocalDateTime requestDateTime;


    @OneToOne(mappedBy = "requestToken", cascade = CascadeType.ALL)
    private Item item;

}
