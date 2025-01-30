package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    DataDto<UUID> create(@Valid RoleSaveRequest request);

    DataDto<UUID> update(@Valid RoleUpdateRequest request);

    DataDto<RoleShortDto> get(UUID id);

    DataDto<List<RoleShortDto>> getAll(RoleRequest request);

    DataDto<Boolean> delete(UUID id);

    DataDto<Boolean> attachRoleToUser(AttachRoleToUserRequest request);
}
