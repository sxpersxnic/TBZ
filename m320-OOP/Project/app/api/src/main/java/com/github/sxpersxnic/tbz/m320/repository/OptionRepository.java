package com.github.sxpersxnic.tbz.m320.repository;

import com.github.sxpersxnic.tbz.m320.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OptionRepository extends JpaRepository<Option, UUID> {}
