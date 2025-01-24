package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {}
