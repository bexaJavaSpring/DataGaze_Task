package com.example.datagaze_task.repository;

import com.example.datagaze_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select t from User t where t.username=?1 ")
    User findByUsername(String username);

    @Query("select t from User t where t.id=?1 ")
    User findByIdAndDeleted(UUID id);
}
