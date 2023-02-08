package com.miu.waafinalproject.service;

import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.model.ResponseModel;

import java.util.HashMap;
import java.util.List;

public interface OwnerService {
    ResponseModel getAllOwnedPropertyList();
    ResponseModel getAllOwnedPropertyApplicationList(HashMap<String, Object> filters);
    List<PropertyApplication> filterProperties(HashMap<String, Object> filters);
}
