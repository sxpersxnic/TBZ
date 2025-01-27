package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import com.github.sxpersxnic.tbz.m320.model.Option;
import com.github.sxpersxnic.tbz.m320.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    @Query("SELECT a FROM Answer a WHERE a.option.id = :optionId")
    List<Answer> findByOptionId(UUID optionId);

    @Query("SELECT a FROM Answer a WHERE a.option.question.id = :questionId")
    List<Answer> findByQuestionId(UUID questionId);

    Answer findByOptionIdAndProfileId(UUID optionId, UUID profileId);

    boolean existsByOptionAndProfile(Option option, Profile profile);
}
