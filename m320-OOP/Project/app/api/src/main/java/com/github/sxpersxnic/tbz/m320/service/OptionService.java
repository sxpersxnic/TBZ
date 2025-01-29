package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.controller.OptionController;
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

/// Service component for {@link Option} entities.
/// @see Option
/// @see CrudService
/// @see OptionController
/// @author sxpersxnic
@Service
public class OptionService implements CrudService<Option, UUID> {

    /// The JPA repository for {@link Option} entities.
    private final OptionRepository optionRepository;

    /// Constructor for the {@link OptionService}.
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }


    /// Find all {@link Option} entities.
    /// @return A list of all {@link Option} entities.
    /// @see OptionRepository#findAll()
    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    /// Find a {@link Option} entity by its ID.
    /// @param id The ID of the {@link Option} entity.
    /// @return The {@link Option} entity with the given ID.
    /// @throws EntityNotFoundException If no {@link Option} entity with the given ID exists.
    @Override
    public Option findById(UUID id) {
        return optionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /// Delete a {@link Option} entity by its ID.
    @Override
    public void delete(UUID id) {
        optionRepository.deleteById(id);
    }


    /// Create a new {@link Option} entity.
    ///
    /// The creation time of the option is set to the current time.
    /// @param option The {@link Option} entity to create.
    /// @return The created {@link Option} entity.
    @Override
    public Option create(Option option) {

        option.setCreatedAt(LocalDateTime.now());

        return optionRepository.save(option);
    }


    /// Update a {@link Option} entity.
    /// @param changing The {@link Option} entity to update. Only the fields that are **not null** will be updated.
    /// @param id The ID of the {@link Option} entity to update.
    /// @return The updated {@link Option} entity.
    /// @throws EntityNotFoundException If no {@link Option} entity with the given ID exists.
    @Override
    public Option update(Option changing, UUID id) {
        Option existing = findById(id);
        merge(changing, existing);
        return optionRepository.save(existing);
    }


    /// Merge two {@link Option} entities.
    ///
    /// Is used in the {@link #update} method.
    ///
    /// If the **changing** entity has a field that is not **null**, it will be updated in the **existing** entity.
    ///
    /// If the **changing** entity has a field that is **null**, it will be ignored.
    ///
    /// If the **changing** entity has a field that is **blank**, an {@link FailedValidationException} will be thrown.
    ///
    /// @param existing The existing {@link Option} entity.
    /// @param changing The changing {@link Option} entity.
    /// @throws FailedValidationException If a field is **blank**.
    ///
    /// @see FailedValidationException
    /// @see StringUtils#isNotBlank
    private void merge(Option changing, Option existing) {
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
