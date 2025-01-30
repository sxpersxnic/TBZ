package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.controller.QuestionController;
import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.repository.AnswerRepository;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import com.github.sxpersxnic.tbz.m320.repository.QuestionRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

/// Service component for {@link Question} entities.
/// @see Question
/// @see CrudService
/// @see QuestionController
/// @author sxpersxnic
@Service
public class QuestionService implements CrudService<Question, UUID> {
    /// The JPA repository for {@link Question} entities.
    private final QuestionRepository questionRepository;
    /// The JPA repository for {@link Answer} entities.
    private final AnswerRepository answerRepository;
    /// The Service for {@link Option} entities.
    private final OptionService optionService;
    /// The Service for {@link Profile} entities.
    private final ProfileService profileService;
    private final OptionRepository optionRepository;

    /// Constructor for the {@link QuestionService}.
    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, OptionService optionService, ProfileService profileService, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.optionService = optionService;
        this.profileService = profileService;
        this.optionRepository = optionRepository;
    }


    /// Find all {@link Question} entities.
    /// @return A list of all {@link Question} entities.
    /// @see QuestionRepository#findAll()
    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    /// Find a {@link Question} entity by its ID.
    /// @param id The ID of the {@link Question} entity.
    /// @return The {@link Question} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link Question} entity with the given ID exists.
    @Override
    public Question findById(UUID id) {
        return questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Question findByOptionId(UUID optionId) {
        UUID id = optionRepository.findQuestionIdByOptionId(optionId).orElseThrow(EntityNotFoundException::new);
        return questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /// Delete a {@link Question} entity by its ID.
    @Override
    public void delete(UUID id) {
        questionRepository.deleteById(id);
    }

    /// Create a new {@link Question} entity.
    /// @param question The {@link Question} entity to create.
    /// @return The created {@link Question} entity.
    /// @throws EntityExistsException If a {@link Question} entity with the same content already exists on the same profile.
    @Override
    public Question create(Question question) {
        question.setTotalAnswerCount(0);
        question.setCreatedAt(ZonedDateTime.now());

        if (questionRepository.existsByContentAndProfileId(question.getContent(), question.getProfile().getId())) {
            throw new EntityExistsException("Question already exists on this profile");
        }

        if (question.getOptions().size() < 2) {
            throw new FailedValidationException(Map.of("Options", List.of("Question must have at least 2 options")));
        }

        Profile profile = profileService.findById(question.getProfile().getId());
        question.setProfile(profile);

        for (Option option : question.getOptions()) {
//            option.setQuestion(question);
            optionService.create(option);
        }

        return questionRepository.save(question);
    }

    /// Update a {@link Question} entity.
    /// @param changing The {@link Question} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link Question} entity to update.
    /// @return The updated {@link Question} entity.
    /// @throws EntityNotFoundException If no {@link Question} entity with the given ID exists.
    @Override
    public Question update(Question changing, UUID id) {
        Question existing = findById(id);
        merge(changing, existing);
        return questionRepository.save(existing);
    }

    /// Merge two {@link Question} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link Question} entity.
    /// @param changing The changing {@link Question} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(Question changing, Question existing) {
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

        if (changing.getTotalAnswerCount() != null) {
            existing.setTotalAnswerCount(changing.getTotalAnswerCount());
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }

    /// Get a page of {@link Question} entities.
    ///
    /// This method is used to get a page of questions with their options and answers.
    /// @param itemsPerPage The number of items per page.
    /// @param currentPage The current page.
    /// @return A list of {@link Question} entities.
    /// @see QuestionRepository#getPage
    /// @see OptionService#getOptionByQuestionId
    /// @see AnswerRepository#findByOptionId
    public List<Question> getQuestionDetails(int itemsPerPage, int currentPage) {
        int offset = (currentPage - 1) * itemsPerPage;
        List<Question> questions = questionRepository.getPage(itemsPerPage, offset);

        for (Question question : questions) {
            List<Option> options = optionService.getOptionByQuestionId(question.getId());

            for (Option option : options) {
                List<Answer> answers = answerRepository.findByOptionId(option.getId());
                option.setAnswers(new HashSet<>(answers));
            }

            question.setOptions(new HashSet<>(options));
        }

        return questions;
    }

    public void updateTotalAnswerCount(UUID id) {
        Question existing = findById(id);
        int answerCount = 0;

        for (Option option : existing.getOptions()) {
            optionService.updateAnswerCount(option.getId());
            answerCount = answerCount + option.getAnswerCount();
        }
        existing.setTotalAnswerCount(answerCount);
        questionRepository.save(existing);
    }
}
