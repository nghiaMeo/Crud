package com.crud.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
