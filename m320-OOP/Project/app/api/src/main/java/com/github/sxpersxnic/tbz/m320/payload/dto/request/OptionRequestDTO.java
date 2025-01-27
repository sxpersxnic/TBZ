package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import lombok.Data;

import java.util.UUID;
import com.github.sxpersxnic.tbz.m320.model.Option;

/// Request Data transfer object of {@link Option}.
///
/// **Contains:**
///
/// - `private UUID questionId;` - Question identifier of the question the option is given for.
///
/// - `private String content;` - Content of option.
/// @author sxpersxnic
@Data
public class OptionRequestDTO {
    private UUID questionId;
    private String content;
}
