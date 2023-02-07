package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;

import java.util.UUID;

public interface PropertyService {
    ResponseModel getAll(Object filters);
    ResponseModel getById(UUID id);
    ResponseModel save(PropertyRequestModel requestModel);
    ResponseModel update(UUID id, PropertyRequestModel requestModel);
    ResponseModel delete(UUID id);
}
