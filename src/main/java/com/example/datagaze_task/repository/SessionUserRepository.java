package com.example.datagaze_task.repository;

import com.example.datagaze_task.entity.SessionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SessionUserRepository extends JpaRepository<SessionUser, UUID> {
    @Query(value = "from SessionUser s join User u on s.user.id=u.id where u.id=:id")
    Optional<SessionUser> findByUserId(@Param("id") UUID id);
}
