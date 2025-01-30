package com.example.datagaze_task.repository;

import com.example.datagaze_task.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>, JpaSpecificationExecutor<Role> {
    @Query("select t from Role t where t.code=?1")
    Role findByCode(String code);

    @Query("select t from Role t where t.id=?1")
    Role findByIdAndDeleted(UUID id);
}
