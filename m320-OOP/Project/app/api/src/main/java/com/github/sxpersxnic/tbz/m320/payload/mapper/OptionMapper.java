package com.github.sxpersxnic.tbz.m320.payload.mapper;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.OptionRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.AnswerResponseDTO;
import com.github.sxpersxnic.tbz.m320.payload.dto.response.OptionResponseDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/// Class to map Option objects to OptionResponseDTO objects and vice versa.
/// @see Option
/// @see OptionResponseDTO
/// @see OptionRequestDTO
/// @author sxpersxnic
public class OptionMapper {

    public static OptionResponseDTO toDTO(Option src) {
        OptionResponseDTO dto = new OptionResponseDTO();
        dto.setId(src.getId());
        dto.setContent(src.getContent());

        Set<AnswerResponseDTO> answers = new HashSet<>();
        for (Answer answer : src.getAnswers()) {
            AnswerResponseDTO answerDto = AnswerMapper.toDTO(answer);
            answers.add(answerDto);
        }
        dto.setAnswers(answers);
        dto.setAnswerCount(dto.getAnswers().size());

        return dto;
    }

    public static Option fromDTO(OptionRequestDTO dto, Question question) {
        Option option = new Option();

        option.setContent(dto.getContent());
        option.setQuestion(question);

        return option;
    }
}
