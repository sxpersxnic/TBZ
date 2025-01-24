package com.github.sxpersxnic.tbz.m320.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sxpersxnic
 */

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public Profile(String username) {
        this.username = username;
        this.createdAt = LocalDateTime.now();
        this.profilePicture = "/default.jpg";
    }

    public Profile() {
        this.createdAt = LocalDateTime.now();
        this.profilePicture = "/default.jpg";
    }
}
