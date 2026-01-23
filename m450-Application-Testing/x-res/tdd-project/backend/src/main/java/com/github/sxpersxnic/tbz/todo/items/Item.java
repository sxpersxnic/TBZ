package com.github.sxpersxnic.tbz.todo.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_completed")
    private boolean isCompleted;
    @Column(name = "tags")
    private Set<String> tags = new HashSet<>();

    @Column(name = "assigned_user_id")
    private UUID assignedUserId;
}
