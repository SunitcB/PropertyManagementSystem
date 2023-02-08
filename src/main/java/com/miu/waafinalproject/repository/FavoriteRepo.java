package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Favorite;
import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepo extends CrudRepository<Favorite, Long> {
    Favorite findByUsersAndProperties(Users users, Property property);
}
