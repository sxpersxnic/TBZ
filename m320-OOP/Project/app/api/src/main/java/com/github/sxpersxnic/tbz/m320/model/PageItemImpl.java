package com.github.sxpersxnic.tbz.m320.model;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.PageItem;

import java.util.Set;

public class PageItemImpl implements PageItem {

    private Question question;

    @Override
    public Profile getProfile() {
        return question.getProfile();
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public Set<Option> getOptions() {
        return question.getOptions();
    }
}
