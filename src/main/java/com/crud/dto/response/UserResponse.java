package com.crud.dto.response;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Set<RoleResponse> roles;
}
