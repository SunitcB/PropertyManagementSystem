package com.miu.waafinalproject.service;

import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;
import com.miu.waafinalproject.model.requestDTO.UserRequestModel;

import java.util.UUID;
import com.miu.waafinalproject.model.ResponseModel;

public interface UserService {
    Users getLoggedInUser();
    ResponseModel getAll(Object filters);
    ResponseModel getById(Long id);
    ResponseModel save(UserRequestModel requestModel);
    ResponseModel update(Long id, UserRequestModel requestModel);
    ResponseModel delete(Long id);
    ResponseModel getLoggedInUserDetails();
}
