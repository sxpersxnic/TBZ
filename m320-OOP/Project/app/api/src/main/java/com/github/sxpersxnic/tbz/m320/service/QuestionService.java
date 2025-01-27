package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.repository.AnswerRepository;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import com.github.sxpersxnic.tbz.m320.repository.QuestionRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService implements CrudService<Question, UUID> {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final OptionRepository optionRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.optionRepository = optionRepository;
    }


    @Override
    public Question findById(UUID id) {
        return questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Question changing, UUID id) {
        Question existing = findById(id);
        merge(changing, existing);
        return questionRepository.save(existing);
    }

    @Override
    public void merge(Question changing, Question existing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getContent() != null) {
            if (StringUtils.isNotBlank(changing.getContent())) {
                existing.setContent(changing.getContent());
            } else {
                errors.put("Content", List.of("Content cannot be empty"));
            }
        }

        if (changing.getDescription() != null) {
            existing.setDescription(changing.getDescription());
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }

    public List<Question> getQuestionDetails(int itemsPerPage, int currentPage) {
        int offset = (currentPage - 1) * itemsPerPage;
        List<Question> questions = questionRepository.getPage(itemsPerPage, offset);

        for (Question question : questions) {
            List<Option> options = optionRepository.getOptionByQuestionId(question.getId());

            for (Option option : options) {
                List<Answer> answers = answerRepository.findByOptionId(option.getId());
                option.setAnswers(new HashSet<>(answers));
            }

            question.setOptions(new HashSet<>(options));
        }

        return questions;
    }
}
