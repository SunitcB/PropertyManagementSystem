package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepo extends CrudRepository<Role, Long> {
    List<Role> findAllByRoleName(String roleName);
}
