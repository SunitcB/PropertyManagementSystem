package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;

import java.util.UUID;

public interface FavoriteService {
    ResponseModel getAll();
    ResponseModel favorite(UUID id);

    ResponseModel deleteById(Long id);

}
