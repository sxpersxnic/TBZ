package com.github.sxpersxnic.tbz.m320.model.details;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class OptionDetails {
    private UUID id;
    private String content;
    private Set<AnswerDetails> answers = new HashSet<>();
    private int answerCount;

    public OptionDetails(UUID id, String content) {
        this.answerCount = 0;
    }
}
