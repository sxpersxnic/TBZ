package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.exceptions.FailedValidationException;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.CrudService;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Service component for {@link Option}
 *
 * @author sxpersxnic
 */
@Service
public class OptionService implements CrudService<Option, UUID> {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Option findById(UUID id) {
        return optionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        optionRepository.deleteById(id);
    }

    @Override
    public Option create(Option option) {

        option.setCreatedAt(LocalDateTime.now());

        return optionRepository.save(option);
    }

    @Override
    public Option update(Option changing, UUID id) {
        Option existing = findById(id);
        merge(changing, existing);
        return optionRepository.save(existing);
    }

    @Override
    public void merge(Option changing, Option existing) {
        Map<String, List<String>> errors = new HashMap<>();

        if (changing.getContent() != null) {
            if (StringUtils.isNotBlank(changing.getContent())) {
                existing.setContent(changing.getContent());
            } else {
                errors.put("Content", List.of("Content cannot be empty!"));
            }
        }

        if (!errors.isEmpty()) {
            throw new FailedValidationException(errors);
        }
    }
}
