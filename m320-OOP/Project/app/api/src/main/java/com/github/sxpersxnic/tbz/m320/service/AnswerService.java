package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.controller.AnswerController;
import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.repository.AnswerRepository;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import com.github.sxpersxnic.tbz.m320.repository.ProfileRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


/// Service component for {@link Answer} entities.
/// @see Answer
/// @see CrudService
/// @see AnswerController
/// @author sxpersxnic
@Service
public class AnswerService implements CrudService<Answer, UUID> {

    /// The JPA repository for {@link Answer} entities.
    private final AnswerRepository answerRepository;
    /// The JPA repository for {@link Option} entities.
    private final OptionRepository optionRepository;
    /// The JPA repository for {@link Profile} entities.
    private final ProfileRepository profileRepository;

    /// Constructor for the {@link AnswerService}.
    public AnswerService(AnswerRepository answerRepository, OptionRepository optionRepository, ProfileRepository profileRepository) {
        this.answerRepository = answerRepository;
        this.optionRepository = optionRepository;
        this.profileRepository = profileRepository;
    }

    /// Find all {@link Answer} entities.
    /// @return A list of all {@link Answer} entities.
    /// @see AnswerRepository#findAll()
    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    /// Find a {@link Answer} entity by its ID.
    /// @param id The ID of the {@link Answer} entity.
    /// @return The {@link Answer} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link Answer} entity with the given ID exists.
    @Override
    public Answer findById(UUID id) {
        return answerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

//    public List<Answer> findByQuestion(UUID id) {
//        return answerRepository.findByQuestionId(id);
//    }

    /// Find all {@link Answer} entities by their {@link Option} ID.
    /// @param id The ID of the {@link Option} entity.
    /// @return A list of all {@link Answer} entities with the given {@link Option} ID.
    /// @see AnswerRepository#findByOptionId
    public List<Answer> findByOption(UUID id) {
        return answerRepository.findByOptionId(id);
    }

    /// Delete a {@link Answer} entity by its ID.
    @Override
    public void delete(UUID id) {
        answerRepository.findAll();
    }

    /// Create a new {@link Answer} entity.
    ///
    /// The password will be encoded before saving the entity.
    /// @param answer The {@link Answer} entity to create.
    /// @return The created {@link Answer} entity.
    @Override
    public Answer create(Answer answer) {
        UUID optionId = answer.getOption().getId();
        UUID profileId = answer.getProfile().getId();

        Option option = optionRepository.findById(optionId).orElseThrow(EntityNotFoundException::new);
        Profile profile = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);

        answer.setOption(option);
        answer.setProfile(profile);

        if (answerRepository.existsByOptionAndProfile(answer.getOption(), answer.getProfile())) {
            return answerRepository.findByOptionIdAndProfileId(answer.getOption().getId(), answer.getProfile().getId());
        }
        return answerRepository.save(answer);
    }

    /// Update a {@link Answer} entity.
    /// @param changing The {@link Answer} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link Answer} entity to update.
    /// @return The updated {@link Answer} entity.
    /// @throws EntityNotFoundException If no {@link Answer} entity with the given ID exists.
    @Override
    public Answer update(Answer changing, UUID id) {
        Answer existing = findById(id);
        merge(changing, existing);
        return answerRepository.save(existing);
    }

    /// Merge two {@link Answer} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link Answer} entity.
    /// @param changing The changing {@link Answer} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(Answer changing, Answer existing) {
        if (changing.getOption() != null) {
            existing.setOption(changing.getOption());
        }
    }
}
