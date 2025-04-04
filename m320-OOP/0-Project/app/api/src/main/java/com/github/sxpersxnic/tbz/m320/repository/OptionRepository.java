package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/// Option repository.
///
/// This interface is used to interact with the database.
///
/// It extends JpaRepository which provides basic CRUD operations.
/// @see JpaRepository
/// @author sxpersxnic
@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {

    @Query("SELECT o FROM Option o WHERE o.question.id = :questionId")
    List<Option> getOptionByQuestionId(UUID questionId);

    @Query("SELECT o.question.id FROM Option o WHERE o.id = :optionId")
    Optional<UUID> findQuestionIdById(UUID optionId);
}
