package com.miu.waafinalproject.service;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface PropertyService {
    ResponseModel getAll(HashMap<String, Object> filters);

    List<Property> filterProperties(HashMap<String, Object> filters);

    ResponseModel getById(UUID id);

    ResponseModel save(PropertyRequestModel requestModel);

    ResponseModel showHideProperty(UUID propertyId, String action);

    ResponseModel update(UUID id, PropertyRequestModel requestModel);

    ResponseModel delete(UUID id);
    ResponseModel makeContingent(UUID id);
    ResponseModel cancelContingent(UUID id);
}
