package com.github.sxpersxnic.tbz.m320.service;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.PageItem;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Question;
import org.springframework.stereotype.Service;

/**
 * @author sxpersxnic
 */
@Service
public class PageService {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ProfileService profileService;
    private final OptionService optionService;

    public PageService(QuestionService questionService, AnswerService answerService, ProfileService profileService, OptionService optionService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.profileService = profileService;
        this.optionService = optionService;
    }
}
