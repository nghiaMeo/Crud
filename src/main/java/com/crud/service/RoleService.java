package com.crud.service;

import com.crud.dto.request.RoleRequest;
import com.crud.dto.response.RoleResponse;
import com.crud.mapper.RoleMapper;
import com.crud.repository.PermissionRepository;
import com.crud.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;


    public RoleResponse create(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);

        var permission = permissionRepository.findAllById(roleRequest.getPermissions());
        var setPermission = new HashSet<>(permission);

        role.setPermissions(setPermission);

        var savedRole = roleRepository.save(role);

        return roleMapper.toRoleResponse(savedRole);
    }

    public List<RoleResponse> findAll() {
        var roles = roleRepository.findAll();
        return roles
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String roleId) {
        roleRepository.deleteById(roleId);
    }


}
