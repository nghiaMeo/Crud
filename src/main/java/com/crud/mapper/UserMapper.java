package com.crud.mapper;


import com.crud.dto.request.UserCreationRequest;
import com.crud.dto.request.UserUpdateRequest;
import com.crud.dto.response.UserResponse;
import com.crud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    User updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);

}
