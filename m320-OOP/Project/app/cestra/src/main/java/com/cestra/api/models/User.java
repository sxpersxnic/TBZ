package com.cestra.api.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Entity
public class User {

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", unique = true, nullable = false)
    private UUID id;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password_hash", unique = true, nullable = false)
    private String password;

    @Column(name="created_at", insertable = false, updatable = false)
    private final LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Template> templates = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Resource> resources = new HashSet<>();

}
