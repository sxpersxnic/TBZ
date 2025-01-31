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

    @Query(value = """
    SELECT *
    FROM questions
    LIMIT :itemsPerPage
    OFFSET :offset
    """, nativeQuery = true)
    List<Question> getPage(int itemsPerPage, int offset);

    boolean existsByContentAndProfileId(String content, UUID profileId);

}
