package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {}
