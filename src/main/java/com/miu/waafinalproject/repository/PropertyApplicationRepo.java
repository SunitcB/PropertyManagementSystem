package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.service.charts.PropertyApplicationCountChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PropertyApplicationRepo extends CrudRepository<PropertyApplication, Long> {
    List<PropertyApplication> findAllByProperty_Id(UUID uuid);
    List<PropertyApplication> findAllByUsers_Id(Long id);
    List<PropertyApplication> findAllByProperty_Owner(Users owner);
    @Query("Select new com.miu.waafinalproject.service.charts.PropertyApplicationCountChartData(p.title, count(pa.id)) from Property p join p.applications pa where p.owner.id = :userId group by pa.id, p.title")
    List<PropertyApplicationCountChartData> getOwnedPropertyCount(long userId);
    PropertyApplication findByProperty_IdAndStatus(UUID uuid, String status);
}
