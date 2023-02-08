package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyApplicationRepo extends CrudRepository<PropertyApplication, Long> {
    List<PropertyApplication> findAllByProperty_Id(UUID uuid);
    List<PropertyApplication> findAllByUsers_Id(Long id);
    List<PropertyApplication> findAllByProperty_Owner(Users owner);
}
