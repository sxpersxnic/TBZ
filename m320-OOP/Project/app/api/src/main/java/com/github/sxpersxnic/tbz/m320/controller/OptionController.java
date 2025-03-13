package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.payload.mapper.OptionMapper;
import com.github.sxpersxnic.tbz.m320.service.OptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.ID_GET;
import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.OPTIONS;

@RestController
@RequestMapping(OPTIONS)
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping(ID_GET)
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            Option option = optionService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(OptionMapper.toDTO(option));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Option not found!");
        }
    }
}
