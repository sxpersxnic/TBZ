package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.AnswerRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.mapper.AnswerMapper;
import com.github.sxpersxnic.tbz.m320.service.AnswerService;
import com.github.sxpersxnic.tbz.m320.service.OptionService;
import com.github.sxpersxnic.tbz.m320.service.ProfileService;
import com.github.sxpersxnic.tbz.m320.service.QuestionService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.*;

@RestController
@RequestMapping(ANSWERS)
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final ProfileService profileService;

    public AnswerController(AnswerService answerService, QuestionService questionService, OptionService optionService, ProfileService profileService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.findAll().stream().map(AnswerMapper::toDTO).toList());
    }

    @GetMapping(ID_GET)
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            Answer answer = answerService.findById(id);
            return ResponseEntity.ok(AnswerMapper.toDTO(answer));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found!");
        }
    }

    @GetMapping(OPTION_ID_GET)
    public ResponseEntity<?> findByOptionId(@RequestParam UUID optionId) {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.findByOptionId(optionId).stream().map(AnswerMapper::toDTO).toList());
    }

    @GetMapping(QUESTION_ID_GET)
    public ResponseEntity<?> findByQuestionId(@RequestParam UUID questionId) {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.findByQuestion(questionId).stream().map(AnswerMapper::toDTO).toList());
    }

    @PostMapping(POST)
    public ResponseEntity<?> create(@Valid @RequestBody AnswerRequestDTO dto) {
        try {
            Answer answer = AnswerMapper.fromDTO(dto);
            Option option = optionService.findById(answer.getOptionId());
            Profile profile = profileService.findById(answer.getProfileId());
            Question question = questionService.findByOptionId(option.getId());

            answer.setProfile(profile);
            answer.setOption(option);
            option.setQuestion(question);
            option.getAnswers().add(answer);
            Answer saved = answerService.create(answer);
            optionService.updateAnswerCount(option.getId());
            questionService.updateTotalAnswerCount(question.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(AnswerMapper.toDTO(saved));
        } catch (EntityExistsException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This profile already answered!");
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile or Option not found!");
        }
    }

    @PatchMapping(PATCH)
    public ResponseEntity<?> update(@Valid @RequestBody AnswerRequestDTO dto, @PathVariable UUID id) {
        try {
            Answer changing = AnswerMapper.fromDTO(dto);
            Answer saved = answerService.update(changing, id);
            questionService.updateTotalAnswerCount(saved.getOption().getQuestion().getId());
            return ResponseEntity.status(HttpStatus.OK).body(AnswerMapper.toDTO(saved));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found!");
        } catch (FailedValidationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request!");
        }
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            UUID optionId = answerService.findById(id).getOptionId();
            UUID questionId = optionService.findById(optionId).getQuestion().getId();
            answerService.delete(id);
            optionService.updateAnswerCount(optionId);
            questionService.updateTotalAnswerCount(questionId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found!");
        }
    }
}
