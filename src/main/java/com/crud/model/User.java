package com.crud.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private LocalDate birthday;

    @Column
    @ManyToMany
    private Set<Role> roles;
}
