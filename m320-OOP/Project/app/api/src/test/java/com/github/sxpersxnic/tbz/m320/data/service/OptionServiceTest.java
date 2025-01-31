package com.github.sxpersxnic.tbz.m320.data.service;

import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Question;
import com.github.sxpersxnic.tbz.m320.repository.OptionRepository;
import com.github.sxpersxnic.tbz.m320.service.OptionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OptionServiceTest {

    @InjectMocks
    private OptionService optionService;

    @Mock
    private OptionRepository optionRepository;

    @Test
    public void checkFindAll_whenOptionsExist_thenOptionsAreReturned() {
        Question question = DataUtil.getTestQuestion();
        List<Option> expectedOptions = DataUtil.getTestOptions(question);
        when(optionRepository.findAll()).thenReturn(expectedOptions);

        List<Option> actualOptions = optionService.findAll();

        assertEquals(expectedOptions.size(), actualOptions.size());
        for (int i = 0; i < expectedOptions.size(); i++) {
            Option expectedOption = expectedOptions.get(i);
            Option actualOption = actualOptions.get(i);
            assertEquals(expectedOption.getId(), actualOption.getId());
            assertEquals(expectedOption.getContent(), actualOption.getContent());
            assertEquals(expectedOption.getQuestion(), actualOption.getQuestion());
        }
    }

    @Test
    public void checkFindById_whenOptionExists_thenOptionIsReturned() {
        Option expectedOption = DataUtil.getTestOption();
        when(optionRepository.findById(expectedOption.getId())).thenReturn(java.util.Optional.of(expectedOption));

        Option actualOption = optionService.findById(expectedOption.getId());

        assertNotNull(actualOption);
        assertEquals(expectedOption.getId(), actualOption.getId());
        assertEquals(expectedOption.getContent(), actualOption.getContent());
        assertEquals(expectedOption.getQuestion(), actualOption.getQuestion());
    }

    @Test
    public void checkFindById_whenNonExistingId_thenEntityNotFoundExceptionIsThrown() {
        when(optionRepository.findById(eq(DataUtil.testUUID(0)))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> optionService.findById(DataUtil.testUUID(0)));
    }

    @Test
    public void checkCreate_whenValidOption_thenOptionIsReturned() {
        Option expectedOption = DataUtil.getTestOption();
        when(optionRepository.save(expectedOption)).thenReturn(expectedOption);

        Option actualOption = optionService.create(expectedOption);

        assertNotNull(actualOption);
        assertEquals(expectedOption.getId(), actualOption.getId());
        assertEquals(expectedOption.getContent(), actualOption.getContent());
        assertEquals(expectedOption.getQuestion(), actualOption.getQuestion());
    }
}
