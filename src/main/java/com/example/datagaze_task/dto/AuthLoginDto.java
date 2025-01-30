package com.example.datagaze_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthLoginDto {
    @NotNull(message = "username.not.null")
    @NotBlank(message = "username.not.blank")
    private String username;

    @NotNull(message = "password.not.null")
    @NotBlank(message = "password.not.blank")
    private String password;
}
