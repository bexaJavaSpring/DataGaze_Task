package com.example.datagaze_task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleUpdateRequest {
    @NotNull(message = "id.must.not.be.null")
    private UUID id;
    private String name;
    private String code;
}
