package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;


/// Response DTO for option
/// @author sxpersxnic
@Data
@EqualsAndHashCode(callSuper = true)
public class OptionResponseDTO extends ResponseDTO {
    private Set<AnswerResponseDTO> answers = new HashSet<>();
    private String content;
    private int answerCount = 0;
}
