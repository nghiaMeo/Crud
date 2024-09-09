package com.crud.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.crud.validation.ValidDate;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {

    @Size(min = 3, max = 20, message = "INVALID_USERNAME")
    private String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;

    @Email(message = "ACCESS_DENIED", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "EMAIL_NOT_EXISTS")
    private String email;

    private String firstname;
    private String lastname;

    @ValidDate(message = "INVALID_BIRTHDAY", min = 18)
    private LocalDate birthday;
}
