package com.crud.mapper;


import com.crud.dto.request.PermissionRequest;
import com.crud.dto.response.PermissionResponse;
import com.crud.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}
