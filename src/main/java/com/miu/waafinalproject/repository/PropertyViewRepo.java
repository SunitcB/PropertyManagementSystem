package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyView;
import com.miu.waafinalproject.service.charts.PropertyViewCountChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyViewRepo extends CrudRepository<PropertyView, Long> {
    PropertyView findByIpAddressAndProperty(String ipAddress, Property property);

    @Query("Select new com.miu.waafinalproject.service.charts.PropertyViewCountChartData(p.title, count(pv.id)) from Property p join p.propertyView pv where pv.property.id is not null and p.owner.id = :userId group by pv.id, p.title")
    List<PropertyViewCountChartData> getPropertyViewCount(long userId);
}
