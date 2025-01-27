package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.OptionRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.QuestionRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.OptionResponseDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.QuestionResponseDTO;

import java.util.*;

/**
 * @author sxpersxnic
 */
public class QuestionMapper {

    public static QuestionResponseDTO toDTO(Question src) {
        QuestionResponseDTO dto = new QuestionResponseDTO();
        List<OptionResponseDTO> options = new ArrayList<>();

        dto.setId(src.getId());
        dto.setContent(src.getContent());
        dto.setDescription(src.getDescription());
        dto.setProfileId(src.getProfile().getId());
        dto.setUsername(src.getProfile().getUsername());
        dto.setProfilePicture(src.getProfile().getProfilePicture());

        for (Option option : src.getOptions()) {
            OptionResponseDTO optionDto = OptionMapper.toDTO(option);
            dto.setTotalAnswerCount(dto.getTotalAnswerCount() + optionDto.getAnswerCount());
            options.add(optionDto);
        }

        dto.setOptions(options);
        dto.setCreatedAt(src.getCreatedAt());

        return dto;
    }

    public static Question fromDTO(QuestionRequestDTO dto) {
        Question question = new Question();
        Profile profile = new Profile();

        UUID profileId = UUID.fromString(dto.getProfileId());
        profile.setId(profileId);

        question.setProfile(profile);
        question.setContent(dto.getContent());
        question.setDescription(dto.getDescription());

        for (OptionRequestDTO optionReq : dto.getOptions()) {
            Option option = OptionMapper.fromDTO(optionReq, question.getId());
            question.getOptions().add(option);
        }

        return question;
    }
}
