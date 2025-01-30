package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.AuthLoginDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.dto.LoginResponse;
import com.example.datagaze_task.dto.UserRegisterDto;
import jakarta.validation.Valid;

import java.util.UUID;

public interface IAuthService {

    DataDto<LoginResponse> login(@Valid AuthLoginDto dto);

    DataDto<UUID> register(@Valid UserRegisterDto dto);

    DataDto<Boolean> logout();
}
