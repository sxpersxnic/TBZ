package com.cestra.api.models;

import com.cestra.api.lib.enums.Provider;
import com.cestra.api.lib.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "resources")
@Entity
public class Resource {

    public Resource() {
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", unique = true, nullable = false)
    private UUID id;

    private Provider provider;

    private String resourceId;

    //* Datatype should be JSON not String
    private String configuration;

    private Status status;

    @Column(name="created_at", insertable = false, updatable = false)
    private final LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
