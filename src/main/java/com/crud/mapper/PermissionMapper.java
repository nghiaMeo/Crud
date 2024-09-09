package com.crud.mapper;

import org.mapstruct.Mapper;

import com.crud.dto.request.PermissionRequest;
import com.crud.dto.response.PermissionResponse;
import com.crud.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}
