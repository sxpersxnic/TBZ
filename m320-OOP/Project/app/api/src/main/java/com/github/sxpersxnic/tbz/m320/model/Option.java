package com.github.sxpersxnic.tbz.m320.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id", "content"})
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "option")
    private Set<Answer> answers = new HashSet<>();

    @Column(name = "answer_count")
    private Integer answerCount;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", updatable = false, insertable = false)
    private ZonedDateTime createdAt;

    public void decreaseAnswerCount() {
        this.setAnswerCount(this.getAnswerCount() - 1);
    }

    public void increaseAnswerCount() {
        this.setAnswerCount(this.getAnswerCount() + 1);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setOption(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setOption(null);
    }

//    public void setQuestion(Question question) {
//        this.question = question;
//        question.addOption(this);
//    }
}
