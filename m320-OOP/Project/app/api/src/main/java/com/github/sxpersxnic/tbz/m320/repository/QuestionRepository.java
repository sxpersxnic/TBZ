package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/// Question repository.
///
/// This interface is used to interact with the database.
///
/// It extends JpaRepository which provides basic CRUD operations.
/// @see JpaRepository
/// @author sxpersxnic
@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    /// Get a page of questions from the database.
    /// @param itemsPerPage The number of items per page.
    /// @param offset The offset.
    /// @return A list of questions.
    /// @see Question
    @Query(value = """
    SELECT *
    FROM questions
    LIMIT :itemsPerPage
    OFFSET :offset
    """, nativeQuery = true)
    List<Question> getPage(int itemsPerPage, int offset);

    /// Check if a question exists by its content and the profile id.
    /// @param content The content of the question.
    /// @param profileId The profile id.
    /// @return True if the question exists, false otherwise.
    boolean existsByContentAndProfileId(String content, UUID profileId);

}
