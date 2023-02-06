package com.miu.waafinalproject.repository;

import com.miu.waafinalproject.domain.Assets;
import com.miu.waafinalproject.domain.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepo extends CrudRepository<Favorite, Long> {
}
