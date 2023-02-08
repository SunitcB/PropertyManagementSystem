package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyView;
import org.springframework.data.repository.CrudRepository;

public interface PropertyViewRepo extends CrudRepository<PropertyView, Long> {
    PropertyView findByIpAddressAndProperty(String ipAddress, Property property);
}
