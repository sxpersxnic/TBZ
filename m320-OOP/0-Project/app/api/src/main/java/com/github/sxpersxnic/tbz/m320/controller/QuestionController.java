package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.payload.dto.request.QuestionRequestDTO;
import com.github.sxpersxnic.tbz.m320.payload.mapper.QuestionMapper;
import com.github.sxpersxnic.tbz.m320.service.QuestionService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.*;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(QUESTIONS)
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Question> questions = questionService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(questions.stream().map(QuestionMapper::toDTO).toList());
    }

    @GetMapping(ID_GET)
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            Question question = questionService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(QuestionMapper.toDTO(question));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
    }

    @GetMapping(OPTION_ID_GET)
    public ResponseEntity<?> findByOptionId(@RequestParam UUID optionId) {
        try {
            Question question = questionService.findByOptionId(optionId);
            return ResponseEntity.status(HttpStatus.OK).body(QuestionMapper.toDTO(question));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
    }

    @GetMapping(GET)
    public ResponseEntity<?> pagination(@RequestParam int itemsPerPage, @RequestParam int currentPage) {
            List<Question> questionPage = questionService.getQuestionDetails(itemsPerPage, currentPage);
            return ResponseEntity.status(HttpStatus.OK).body(questionPage.stream().map(QuestionMapper::toDTO).toList());
    }

    @PostMapping(POST)
    public ResponseEntity<?> create(@Valid @RequestBody QuestionRequestDTO dto) {
        try {
            Question question = QuestionMapper.fromDTO(dto);
            Question saved = questionService.create(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(QuestionMapper.toDTO(saved));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Question Request!");
        } catch (EntityExistsException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Question already exists on this profile!");
        }
    }

    //TODO: update and delete methods
}
