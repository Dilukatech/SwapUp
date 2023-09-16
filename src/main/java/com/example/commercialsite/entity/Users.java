package com.example.commercialsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor //
@NoArgsConstructor
@Getter
@Entity(name = "User") // hibernate annotations
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "first_name",length = 100)
    private String firstName;

    @Column(name = "last_name",length = 100)
    private String lastName;

    @Column(name = "nic",length = 13)
    private String nic;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "address")
    private String address;

    @Column(name="role")
    private String role;

    @Column(name = "active_status")
    private boolean activeStatus;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "verified")
    private boolean verified;


}

