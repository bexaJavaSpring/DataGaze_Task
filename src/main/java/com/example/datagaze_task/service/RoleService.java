package com.example.datagaze_task.service;

import com.example.datagaze_task.dto.*;
import com.example.datagaze_task.entity.Role;
import com.example.datagaze_task.entity.User;
import com.example.datagaze_task.exception.CustomNotFoundException;
import com.example.datagaze_task.mapper.RoleMapper;
import com.example.datagaze_task.repository.RoleRepository;
import com.example.datagaze_task.repository.UserRepository;
import com.example.datagaze_task.specification.RoleSpecification;
import com.example.datagaze_task.specification.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{
    private final RoleRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<UUID> create(RoleSaveRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setCode(request.getCode());
        Role save = repository.save(role);
        return new DataDto<>(save.getId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<UUID> update(RoleUpdateRequest request) {
        Role role = repository.findByIdAndDeleted(request.getId());
        if (role == null)
            throw new CustomNotFoundException("role.not.found");

        role.setName(request.getName());
        role.setCode(request.getCode());
        repository.save(role);
        return new DataDto<>(role.getId());
    }

    @Override
    public DataDto<RoleShortDto> get(UUID id) {
        Role role = repository.findByIdAndDeleted(id);
        if (role == null)
            throw new CustomNotFoundException("role.not.found");
        return new DataDto<>(mapper.toRoleDto(role));
    }

    @Override
    public DataDto<List<RoleShortDto>> getAll(RoleRequest request) {
        List<RoleShortDto> list = repository.findAll(new RoleSpecification(request),
                        SearchSpecification.getPageable(request.getPage(), request.getLimit(), "createdAt"))
                .map(mapper::toRoleDto)
                .toList();
        return new DataDto<>(list);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<Boolean> delete(UUID id) {
        Role role = repository.findByIdAndDeleted(id);
        if (role == null)
            throw new CustomNotFoundException("role.not.found");

        repository.delete(role);
        return new DataDto<>(true);
    }

    @Override
    public DataDto<Boolean> attachRoleToUser(AttachRoleToUserRequest request) {
        User user = userRepository.findByIdAndDeleted(request.getUserId());
        if (user == null)
            throw new CustomNotFoundException("user.not.found");

        Role role = roleRepository.findByIdAndDeleted(request.getRoleId());
        if (role == null)
            throw new CustomNotFoundException("role.not.found");

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return new DataDto<>(true);
    }
}
