package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author sxpersxnic
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProfileResponseDTO extends ResponseDTO {
    private UserResponseDTO user;
    private String username;
    private String profilePicture;
    private List<QuestionResponseDTO> questions;
    private List<AnswerResponseDTO> answers;
}
