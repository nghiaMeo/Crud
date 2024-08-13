package com.crud.repository;

import com.crud.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository  extends JpaRepository<Permission, String> {

}
