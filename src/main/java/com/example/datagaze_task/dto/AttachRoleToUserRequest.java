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
public class AttachRoleToUserRequest {
    @NotNull(message = "role.id.should.not.be.null")
    private UUID roleId;
    @NotNull(message = "user.id.should.not.be.null")
    private UUID userId;
}
