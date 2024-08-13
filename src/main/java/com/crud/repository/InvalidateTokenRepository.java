package com.crud.repository;

import com.crud.model.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidateTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
