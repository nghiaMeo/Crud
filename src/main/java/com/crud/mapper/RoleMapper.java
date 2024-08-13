package com.crud.mapper;


import com.crud.dto.request.RoleRequest;
import com.crud.dto.response.RoleResponse;
import com.crud.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
