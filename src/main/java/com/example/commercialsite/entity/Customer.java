package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Customer")
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @OneToOne
    @JoinColumn(name = "users_ID")
    private Users users;


}
