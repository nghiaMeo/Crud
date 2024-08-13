package com.crud.controller;

import com.crud.dto.request.RoleRequest;
import com.crud.dto.response.ApiResponse;
import com.crud.dto.response.RoleResponse;
import com.crud.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .results(roleService.create(roleRequest))
                .build();

    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .results(roleService.findAll())
                .build();

    }

    @DeleteMapping("/{roleId}")
    public ApiResponse<Void> deleteRole(@PathVariable String roleId) {
        roleService.delete(roleId);
        return ApiResponse.<Void>builder().build();
    }
}
