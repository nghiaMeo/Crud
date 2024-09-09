package com.crud.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest {
    private String name;

    private String description;
}
