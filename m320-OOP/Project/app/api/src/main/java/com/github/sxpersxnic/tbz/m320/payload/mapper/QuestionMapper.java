package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.QuestionRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.QuestionResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
public class QuestionMapper {

    public static QuestionResponseDTO toDTO(Question src) {
        QuestionResponseDTO dto = new QuestionResponseDTO();

        dto.setId(src.getId());
        dto.setOwnerId(src.getProfile().getId());

        List<UUID> optionIds = src
                .getOptions()
                .stream()
                .map(Option::getId)
                .toList();
        dto.setOptionIds(optionIds);

        List<UUID> answerIds = src
                .getAnswers()
                .stream()
                .map(Answer::getId)
                .toList();
        dto.setAnswerIds(answerIds);

        dto.setContent(src.getContent());
        dto.setDescription(src.getDescription());

        dto.setCreatedAt(src.getCreatedAt());

        return dto;
    }

    public static Question fromDTO(QuestionRequestDTO dto) {
        Question question = new Question();

        question.setContent(dto.getContent());
        question.setDescription(dto.getDescription());

        for (UUID optionId : dto.getOptionIds()) {
            Option option = new Option();
            option.setId(optionId);

            question.getOptions().add(option);
        }

        return question;
    }
}
