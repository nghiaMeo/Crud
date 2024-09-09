package com.crud.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.crud.dto.request.UserCreationRequest;
import com.crud.dto.request.UserUpdateRequest;
import com.crud.dto.response.ApiResponse;
import com.crud.dto.response.UserResponse;
import com.crud.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .results(userService.createUser(request))
                .build();
    }

    @GetMapping("/users")
    ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .results(userService.getUsers())
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .results(userService.getMyInfo())
                .build();
    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUserById(
            @Valid @RequestBody UserUpdateRequest userUpdateRequest, @PathVariable("userId") String userId) {
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
