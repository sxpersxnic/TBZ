package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Answer;

import java.util.List;
import java.util.UUID;

public class AnswerService implements CrudService<Answer, UUID> {
    @Override
    public Answer findById(UUID id) {
        return null;
    }

    @Override
    public List<Answer> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Answer create(Answer answer) {
        return null;
    }

    @Override
    public Answer update(Answer changing, UUID id) {
        return null;
    }

    @Override
    public void merge(Answer changing, Answer existing) {

    }
}
