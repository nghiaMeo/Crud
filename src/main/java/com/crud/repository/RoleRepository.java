package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
