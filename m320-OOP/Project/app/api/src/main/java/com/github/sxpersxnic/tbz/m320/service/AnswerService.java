package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.details.AnswerDetails;
import com.github.sxpersxnic.tbz.m320.repository.AnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Service
public class AnswerService implements CrudService<Answer, UUID> {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
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
        answer.setCreatedAt(LocalDateTime.now());
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

    public AnswerDetails buildAnswerDetails(Answer answer) {
        AnswerDetails answerDetails = new AnswerDetails();

        answerDetails.setProfileId(answer.getProfile().getId());
        answerDetails.setUsername(answer.getProfile().getUsername());
        answerDetails.setProfilePicture(answer.getProfile().getProfilePicture());
        answerDetails.setOptionId(answer.getOption().getId());
        answerDetails.setCreatedAt(answer.getCreatedAt());

        return answerDetails;
    }
}
