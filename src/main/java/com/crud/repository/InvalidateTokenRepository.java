package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.InvalidatedToken;

public interface InvalidateTokenRepository extends JpaRepository<InvalidatedToken, String> {}
