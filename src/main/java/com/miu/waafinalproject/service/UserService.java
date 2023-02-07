package com.miu.waafinalproject.service;

import com.miu.waafinalproject.domain.Users;
import com.miu.waafinalproject.model.ResponseModel;

public interface UserService {
    Users getLoggedInUser();
    ResponseModel getLoggedInUserDetails();
}
