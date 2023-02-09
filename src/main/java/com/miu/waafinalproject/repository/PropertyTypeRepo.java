package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.PropertyType;
import com.miu.waafinalproject.service.charts.OwnerPropertyTypeCountChartData;
import com.miu.waafinalproject.service.charts.PropertyTypeCountChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyTypeRepo extends CrudRepository<PropertyType, Long> {
    PropertyType findByName(String type);

    @Query("Select new com.miu.waafinalproject.service.charts.PropertyTypeCountChartData(pt.name, count(p.id))  from Property p left join p.propertyType pt group by pt.name")
    List<PropertyTypeCountChartData> getPropertyTypeCountChartData();

    @Query("Select new com.miu.waafinalproject.service.charts.OwnerPropertyTypeCountChartData(pt.name, count(p.id))  from Property p left join p.propertyType pt where p.owner.id = :userId group by pt.name")
    List<OwnerPropertyTypeCountChartData> getOwnerPropertyTypeCountChartData(long userId);
}
