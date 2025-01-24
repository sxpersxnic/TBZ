package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Option;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OptionService implements CrudService<Option, UUID> {
    @Override
    public Option findById(UUID id) {
        return null;
    }

    @Override
    public List<Option> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Option create(Option option) {
        return null;
    }

    @Override
    public Option update(Option changing, UUID id) {
        return null;
    }

    @Override
    public void merge(Option changing, Option existing) {

    }
}
