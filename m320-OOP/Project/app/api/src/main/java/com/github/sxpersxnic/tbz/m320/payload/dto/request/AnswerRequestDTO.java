package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.payload.mapper.AnswerMapper;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.AnswerResponseDTO;

/// Request Data transfer object of {@link Answer}.
///
/// **Contains:**
///
/// - `private UUID profileId;` - Profile that is submitting the answer.
///
/// - `private UUID optionId;` - Option that is submitted to.
///
/// @see AnswerMapper
/// @see AnswerResponseDTO
///
/// @author sxpersxnic
@Data
public class AnswerRequestDTO {
 /// Profile that is submitting the answer.
 ///
 /// @see Profile
 @NotBlank(message = "ProfileId must not be blank")
 private String profileId;

 /// Option that is submitted to.
 ///
 /// @see Option
 @NotBlank(message = "OptionId must not be blank")
 private String optionId;
}
