package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyRepo extends JpaRepository<Property, UUID> {
    List<Property> findAllByOwner(Users owner);
    List<Property> findAllByIsActiveAndOwner_IsActive(boolean isActive, boolean isOwnerActive);
    List<Property> findTop10ByPropertyStatusOrderByPriceDesc(String status);
}
