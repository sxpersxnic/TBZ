package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/// Profile repository.
///
/// This interface is used to interact with the database.
///
/// It extends JpaRepository which provides basic CRUD operations.
/// @see JpaRepository
/// @author sxpersxnic
@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {}
