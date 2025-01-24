package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    boolean existsByIdAndQuestionId(UUID id, UUID questionId);
}
