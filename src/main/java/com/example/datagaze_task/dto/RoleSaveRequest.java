package com.example.datagaze_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleSaveRequest {
    @NotNull(message = "role.name.must.not.be.null")
    @NotBlank(message = "role.name.must.not.be.blank")
    private String name;
    private String code;
}
