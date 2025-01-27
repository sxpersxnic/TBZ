package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.AnswerRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.AnswerResponseDTO;

import java.util.UUID;

public class AnswerMapper {

    public static AnswerResponseDTO toDTO(Answer answer) {
        AnswerResponseDTO dto = new AnswerResponseDTO();

        dto.setId(answer.getId());
        dto.setOptionId(answer.getOption().getId());
        dto.setProfileId(answer.getProfile().getId());
        dto.setProfilePicture(answer.getProfile().getProfilePicture());
        dto.setUsername(answer.getProfile().getUsername());
        dto.setCreatedAt(answer.getCreatedAt());

        return dto;
    }

    public static Answer fromDTO(AnswerRequestDTO dto) {
        Answer answer = new Answer();
        Profile profile = new Profile();
        Option option = new Option();

        UUID profileId = UUID.fromString(dto.getProfileId());
        profile.setId(profileId);

        UUID optionId = UUID.fromString(dto.getOptionId());
        option.setId(optionId);

        answer.setProfile(profile);
        answer.setOption(option);

        return answer;
    }
}
