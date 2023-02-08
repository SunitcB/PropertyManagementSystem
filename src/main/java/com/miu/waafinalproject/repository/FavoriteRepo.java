package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Favorite;
import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FavoriteRepo extends CrudRepository<Favorite, Long> {
    Favorite findByUsersAndProperties(Users users, Property property);

    Favorite findByUsersAndProperties_Id(Users users, UUID id);
}
