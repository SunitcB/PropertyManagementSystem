package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;

public interface OwnerService {
    ResponseModel getAllOwnedPropertyList();
    ResponseModel getAllOwnedPropertyApplicationList();
}
