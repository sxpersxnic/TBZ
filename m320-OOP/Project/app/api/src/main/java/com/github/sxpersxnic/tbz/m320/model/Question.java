package com.github.sxpersxnic.tbz.m320.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @OneToMany(mappedBy = "question")
    private Set<Option> options = new HashSet<>();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "description")
    private String description;

    @Column(name = "total_answer_count")
    private Integer totalAnswerCount;

    @Column(name = "created_at", updatable = false, insertable = false)
    private ZonedDateTime createdAt;
}
