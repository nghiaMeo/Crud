package com.crud.model;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    private String name;

    private String description;

    @ManyToMany
    private Set<Permission> permissions;
}
