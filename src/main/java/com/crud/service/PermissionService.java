package com.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.dto.request.PermissionRequest;
import com.crud.dto.response.PermissionResponse;
import com.crud.mapper.PermissionMapper;
import com.crud.model.Permission;
import com.crud.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> findAll() {
        var permissions = permissionRepository.findAll();

        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
