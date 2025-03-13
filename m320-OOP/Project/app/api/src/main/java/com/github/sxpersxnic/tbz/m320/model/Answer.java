package com.github.sxpersxnic.tbz.m320.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id", "profile", "option"})
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    private ZonedDateTime createdAt;

    public UUID getOptionId() {
        return option.getId();
    }

    public UUID getProfileId() {
        return profile.getId();
    }
}
