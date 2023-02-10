package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Favorite;
import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.service.charts.FavoriteCountChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepo extends CrudRepository<Favorite, Long> {
    Favorite findByUsersAndProperties(Users users, Property property);
    Favorite findByUsersAndProperties_Id(Users users, UUID id);
    List<Favorite> findAllByUsers(Users users);
    @Query("Select pf.properties.title, count(pf) from Property p join p.favorites pf where p.owner.id = :userId group by pf")
    List<FavoriteCountChartData> getFavouriteChartData(long userId);
}
