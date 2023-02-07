package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.PropertyOption;
import org.springframework.data.repository.CrudRepository;

public interface PropertyOptionRepo extends CrudRepository<PropertyOption, Long> {
    PropertyOption findByType(String type);
}
