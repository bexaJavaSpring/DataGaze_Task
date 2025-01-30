package com.example.datagaze_task.mapper;

import com.example.datagaze_task.dto.RoleShortDto;
import com.example.datagaze_task.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleShortDto toRoleDto(Role role) {
        return RoleShortDto.builder()
                .id(role.getId())
                .name(role.getName())
                .code(role.getCode())
                .build();
    }
}
