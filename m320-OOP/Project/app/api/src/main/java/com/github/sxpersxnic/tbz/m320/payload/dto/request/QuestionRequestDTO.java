package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.grammars.hql.HqlParser;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/// Request Data transfer object of {@link Question}.
///
/// **Contains:**
/// - `private String profileId;` - Profile identifier of the question's author.
/// - `private String content;` - Content of the question.
/// - `private String description;` - Optional description of the question.
/// - `private List<OptionRequestDTO> options;` - Given options of the question.
/// - `private LocalDateTime createdAt;` - Local (with timezone) date and time of the question's creation.
///
/// @author sxpersxnic
@Data
public class QuestionRequestDTO {

    /// Profile identifier of the {@link Question}'s author.
    /// @see Profile
    @NotBlank(message = "ProfileId must not be blank!")
    private String profileId;

    /// Content of the {@link Question}.
    @NotBlank(message = "Content must not be blank!")
    private String content;

    /// Optional description of the {@link Question}.
    private String description;

    /// Given options of the {@link Question}.
    /// @see Option
    private List<OptionRequestDTO> options;

    /// Local (with timezone) date and time of the {@link Question}'s creation.
    private final ZonedDateTime createdAt = ZonedDateTime.now();
}
