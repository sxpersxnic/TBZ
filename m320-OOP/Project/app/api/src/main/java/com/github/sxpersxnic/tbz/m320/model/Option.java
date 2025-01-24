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
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "option")
    private Set<Answer> answers = new HashSet<>();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}
