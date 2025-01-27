package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import com.github.sxpersxnic.tbz.m320.model.Question;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/// Request Data transfer object of {@link Question}.
///
/// **Contains:**
/// - `private String profileId;` - Profile identifier of the questions author.
/// - `private String content;` - Content of question.
/// - `private String description;` - Optional description of question.
/// - `private List<OptionRequestDTO> options;` - Given options of the question.
/// - `private LocalDateTime createdAt;` - Local (with timezone) date and time of the question's creation.
///
/// @author sxpersxnic
@Data
public class QuestionRequestDTO {

    @NotBlank(message = "Profile ID cannot be empty!")
    private String profileId;
    @NotBlank(message = "Content cannot be empty!")
    private String content;

    private String description;

    @Min(value = 2, message = "A minimum of two options is required")
    private List<OptionRequestDTO> options;

    private final LocalDateTime createdAt = LocalDateTime.now();
}
