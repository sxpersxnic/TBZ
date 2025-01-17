package com.m320.api.payload.mapper;

import com.m320.api.model.Question;
import com.m320.api.payload.dto.request.QuestionRequestDTO;

public class QuestionMapper {

    public static Question fromDTO(QuestionRequestDTO dto)
    {
        Question question = new Question();

        question.setQuestion(dto.getQuestion());
        question.setDescription(dto.getDescription());

        return question;
    }

    public static QuestionRequestDTO toDTO(Question src)
    {
        QuestionRequestDTO dto = new QuestionRequestDTO();

        dto.setQuestion(src.getQuestion());
        dto.setDescription(src.getDescription());

        return dto;
    }
}
