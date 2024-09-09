package com.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    int countByEmail(String email);
}
