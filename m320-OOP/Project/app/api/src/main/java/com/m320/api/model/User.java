package com.m320.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="users", schema="auth")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "encrypted_password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Profile> profile = new ArrayList<>();

}
