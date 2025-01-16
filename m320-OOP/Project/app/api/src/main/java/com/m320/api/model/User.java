package com.m320.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Table(name="users", schema="auth")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "encrypted_password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile profile;

}
