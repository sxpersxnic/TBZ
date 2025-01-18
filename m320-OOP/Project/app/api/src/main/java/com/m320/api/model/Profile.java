package com.m320.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "profile_picture")
    private String profilePicture = "/default.png";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
//    private Set<Question> questions = new LinkedHashSet<>();

//    @ManyToMany
//    @JoinTable(
//            name = "followers",
//            joinColumns = @JoinColumn(name = "follower_id"),
//            inverseJoinColumns = @JoinColumn(name = "followee_id")
//    )
//    private Set<Profile> following = new LinkedHashSet<>();
//
//    @ManyToMany(mappedBy = "following")
//    private Set<Profile> followers = new LinkedHashSet<>();
}
