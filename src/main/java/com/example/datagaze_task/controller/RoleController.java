package com.example.datagaze_task.controller;

import com.example.datagaze_task.dto.*;
import com.example.datagaze_task.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role controller")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService service;

    @PostMapping(value = "/create")
    @Operation(description = "API for creating role")
    public ResponseEntity<DataDto<UUID>> create(@RequestBody @Valid RoleSaveRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/update")
    @Operation(description = "API for updating role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<UUID>> update(@RequestBody @Valid RoleUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "API for get a role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<RoleShortDto>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<List<RoleShortDto>>> getAll(RoleRequest request) {
        return ResponseEntity.ok(service.getAll(request));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping(value = "/attach-to-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<Boolean>> attachRoleToUser(@RequestBody @Valid AttachRoleToUserRequest request) {
        return ResponseEntity.ok(service.attachRoleToUser(request));
    }
}
