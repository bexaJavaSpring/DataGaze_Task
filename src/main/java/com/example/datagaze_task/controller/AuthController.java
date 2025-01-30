package com.example.datagaze_task.controller;

import com.example.datagaze_task.dto.AuthLoginDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.dto.LoginResponse;
import com.example.datagaze_task.dto.UserRegisterDto;
import com.example.datagaze_task.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService service;

    @PostMapping("/login")
    public ResponseEntity<DataDto<LoginResponse>> login(@RequestBody @Valid AuthLoginDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<DataDto<UUID>> register(@RequestBody @Valid UserRegisterDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }
    @PostMapping(value = "/logout")
    @PreAuthorize(value = "isAuthenticated()")
    @Operation(description = "Logging out from the platform")
    public ResponseEntity<DataDto<Boolean>> logout() {
        return ResponseEntity.ok(service.logout());
    }
}
