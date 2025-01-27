package com.github.sxpersxnic.tbz.m320.model.details;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class QuestionDetails {
    private UUID id;
    private String content;
    private String description;
    private LocalDateTime createdAt;

    private UUID profileId;
    private String username;
    private String profilePicture;

    Set<OptionDetails> options;

}
