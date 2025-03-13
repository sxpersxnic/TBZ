package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

/// User repository.
///
/// This interface is used to interact with the database.
///
/// It extends JpaRepository which provides basic CRUD operations.
/// @see JpaRepository
/// @author sxpersxnic
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
}
