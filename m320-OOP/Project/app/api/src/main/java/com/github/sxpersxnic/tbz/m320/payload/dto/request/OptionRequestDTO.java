package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import lombok.Data;

import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Question;

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

    /// Question identifier of the question the option is given for.
    ///
    /// @see Question
    private String questionId;

    /// Content of the option.
    private String content;
}
