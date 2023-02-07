package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.PropertyType;
import org.springframework.data.repository.CrudRepository;

public interface PropertyTypeRepo extends CrudRepository<PropertyType, Long> {
    PropertyType findByName(String type);
}
