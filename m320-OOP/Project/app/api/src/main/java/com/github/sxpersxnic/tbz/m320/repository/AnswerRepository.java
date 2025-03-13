package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/// Answer repository.
///
/// This interface is used to interact with the database.
///
/// It extends JpaRepository which provides basic CRUD operations.
/// @see JpaRepository
/// @author sxpersxnic
@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    @Query("SELECT a FROM Answer a WHERE a.option.id = :optionId")
    List<Answer> findByOptionId(UUID optionId);

    @Query("SELECT a FROM Answer a WHERE a.option.question.id = :questionId")
    List<Answer> findByQuestionId(UUID questionId);

    @Query("SELECT a FROM Answer a WHERE a.option.id = :optionId AND a.profile.id = :profileId")
    Optional<Answer> findByOptionIdAndProfileId(UUID optionId, UUID profileId);

    @Query("SELECT a FROM Answer a WHERE a.option.question.id = :questionId AND a.profile.id = :profileId")
    Optional<Answer> findByQuestionIdAndProfileId(UUID questionId, UUID profileId);

    @Query("SELECT COUNT(a) FROM Answer a WHERE a.option.id = :optionId AND a.profile.id = :profileId")
    boolean existsByOptionAndProfile(UUID optionId, UUID profileId);

    @Query("SELECT COUNT(a) FROM Answer a WHERE a.option.id = :id")
    int countByOptionId(UUID id);

    @Query("SELECT COUNT(a) FROM Answer a WHERE a.option.question.id = :id")
    int countByQuestionId(UUID id);
}
