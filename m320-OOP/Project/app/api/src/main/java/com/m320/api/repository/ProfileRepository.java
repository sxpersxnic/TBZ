package com.m320.api.repository;

import com.m320.api.model.Profile;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "0")})
    @Query("SELECT p FROM Profile p WHERE p.id = :id")
    Optional<Profile> findByIdForUpdate(UUID id);

    @Query("SELECT p FROM Profile p WHERE p.user.id = :id")
    Optional<Profile> findByUserId(UUID id);

    boolean existsByUserId(UUID id);
    boolean existsByUsername(String username);

}
