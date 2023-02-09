package com.miu.waafinalproject.service;

import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.UserRequestModel;

public interface UserService {
    Users getLoggedInUser();
    boolean hasToken();
    boolean checkIfCurrentUserHasRole(String roleName);
    ResponseModel getAll(Object filters);
    ResponseModel getById(Long id);
    ResponseModel save(UserRequestModel requestModel);
    ResponseModel update(Long id, UserRequestModel requestModel);
    ResponseModel delete(Long id);
    ResponseModel getLoggedInUserDetails();

    ResponseModel changeActiveStatus(Long id);
}
