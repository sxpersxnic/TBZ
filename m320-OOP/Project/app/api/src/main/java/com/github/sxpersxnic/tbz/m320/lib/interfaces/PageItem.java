package com.github.sxpersxnic.tbz.m320.lib.interfaces;

import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.Question;

import java.util.Set;

public interface PageItem {
    Question getQuestion();
    void setQuestion(Question question);
    Profile getProfile();
    Set<Option> getOptions();
}
