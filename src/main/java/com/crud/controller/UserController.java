package com.crud.controller;

import com.crud.dto.request.UserCreationRequest;
import com.crud.dto.request.UserUpdateRequest;
import com.crud.dto.response.ApiResponse;
import com.crud.dto.response.UserResponse;
import com.crud.model.User;
import com.crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResults(userService.createUser(request));

        return response;
    }

    @GetMapping("/users")
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
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
    UserResponse updateUserById(@Valid @RequestBody UserUpdateRequest userUpdateRequest, @PathVariable("userId") String userId) {
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }
}
