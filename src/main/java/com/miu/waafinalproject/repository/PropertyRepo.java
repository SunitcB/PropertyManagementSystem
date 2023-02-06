package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepo extends JpaRepository<Property, UUID> {
}
