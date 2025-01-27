package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;
import com.github.sxpersxnic.tbz.m320.model.Answer;

/// Request Data transfer object of {@link Answer}.
///
/// **Contains:**
///
/// - `private UUID profileId;` - Profile that is submitting the answer.
///
/// - `private UUID optionId;` - Option that is submitted to.
/// @author sxpersxnic
@Data
public class AnswerRequestDTO {

 @NotBlank(message = "Profile ID cannot be blank!")
 private String profileId;

 @NotBlank(message = "Option ID cannot be blank!")
 private String optionId;
}
