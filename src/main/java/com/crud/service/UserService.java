package com.crud.service;

import com.crud.dto.request.UserCreationRequest;
import com.crud.dto.request.UserUpdateRequest;
import com.crud.dto.response.UserResponse;
import com.crud.exception.AppException;
import com.crud.exception.ErrorCode;
import com.crud.mapper.UserMapper;
import com.crud.model.Role;
import com.crud.model.User;
import com.crud.repository.RoleRepository;
import com.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public User createUser(UserCreationRequest request) {
        var username = request.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        var email = request.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }


        var birthday = request.getBirthday();
        if (birthday == null) {
            throw new AppException(ErrorCode.INVALID_BIRTHDAY);
        }


        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roleOptional = roleRepository.findById("USER");

        Set<Role> roleSet = roleOptional.map(Collections::singleton)
                .map(HashSet::new)
                .orElseGet(HashSet::new);
        user.setRoles(roleSet);

        return userRepository.save(user);
    }

    public UserResponse updateUser(String id, UserUpdateRequest updateRequest) {
        var user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        var currentUsername = user.getUsername();
        var updateUser = userMapper.updateUser(user, updateRequest);

        var updatedUsername = updateUser.getUsername();

        if (updatedUsername == null) {
            user.setUsername(currentUsername);
        }
        user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));

        var roles = roleRepository.findAllById(updateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));
        var saveUpdateUser = userRepository.save(user);

        return userMapper.toUserResponse(saveUpdateUser);

    }

    //    @PreAuthorize(value = "hasRole('ADMIN')")
    @PreAuthorize(value = "hasAnyAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {

        var context = SecurityContextHolder.getContext();
        var username = context.getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);


    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
