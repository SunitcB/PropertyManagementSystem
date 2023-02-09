package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.LoginRequestModel;

public interface AuthenticationService {
    ResponseModel authenticateUser(LoginRequestModel loginRequestModel);
    ResponseModel forgotPassword(String userName);
    ResponseModel resetPassword(LoginRequestModel forgotModel, String token);
}
