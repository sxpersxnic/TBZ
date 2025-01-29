package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import com.github.sxpersxnic.tbz.m320.model.Option;

/// Request Data transfer object of {@link Option}.
///
/// **Contains:**
///
/// - `private UUID questionId;` - Question identifier of the question the option is given for.
///
/// - `private String content;` - Content of the option.
/// @author sxpersxnic
@Data
public class OptionRequestDTO {

    /// Content of the option.
    @NotBlank(message = "Content must not be blank")
    private String content;
}
