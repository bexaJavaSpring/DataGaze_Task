package com.example.datagaze_task.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String access_token;
    private String refresh_token;
}
