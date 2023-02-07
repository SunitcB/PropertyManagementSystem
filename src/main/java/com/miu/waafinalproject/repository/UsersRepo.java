package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
