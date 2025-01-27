package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.repository.AnswerRepository;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import com.github.sxpersxnic.tbz.m320.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Service component for {@link Answer}
 *
 * @author sxpersxnic
 */
@Service
public class AnswerService implements CrudService<Answer, UUID> {

    private final AnswerRepository answerRepository;
    private final OptionRepository optionRepository;
    private final ProfileRepository profileRepository;

    public AnswerService(AnswerRepository answerRepository, OptionRepository optionRepository, ProfileRepository profileRepository) {
        this.answerRepository = answerRepository;
        this.optionRepository = optionRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Answer findById(UUID id) {
        return answerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        answerRepository.findAll();
    }

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

    @Override
    public Answer update(Answer changing, UUID id) {
        Answer existing = findById(id);
        merge(changing, existing);
        return answerRepository.save(existing);
    }

    @Override
    public void merge(Answer changing, Answer existing) {
        if (changing.getOption() != null) {
            existing.setOption(changing.getOption());
        }
    }

    public List<Answer> findByQuestion(UUID id) {
        return answerRepository.findByQuestionId(id);
    }

    public List<Answer> findByOption(UUID id) {
        return answerRepository.findByOptionId(id);
    }
}
