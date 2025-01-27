package com.github.sxpersxnic.tbz.m320.model.details;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = "profileId")
public class AnswerDetails {
    private UUID profileId;
    private String username;
    private String profilePicture;
    private UUID optionId;
    private LocalDateTime createdAt;

    public AnswerDetails(UUID profileId, String username, String profilePicture, UUID optionId, LocalDateTime createdAt) {
        this.profileId = profileId;
        this.username = username;
        this.profilePicture = profilePicture;
        this.optionId = optionId;
        this.createdAt = createdAt;
    }
}
